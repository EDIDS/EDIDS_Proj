package com.extraction;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.extraction.map.Building;
import com.extraction.player.Player;
import com.extraction.player.PlayerData;
import com.extraction.utils.GameSaveTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The S3Uploader class is responsible for uploading files to an Amazon S3 bucket.
 * It also provides functionality to save the game state, download all games, and generate a list of games.
 */
public class S3Uploader {
    private String bucketName;
    private AmazonS3 s3Client;
    private Gson gson = new GsonBuilder().registerTypeAdapter(GameSave.class, new GameSaveTypeAdapter()).setPrettyPrinting().create();

    /**
     * Constructs a new S3Uploader with the given AWS profile, region, and bucket name.
     * @param profile The AWS profile.
     * @param region The AWS region.
     * @param bucketName The name of the S3 bucket.
     * @throws IOException If an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.
     */
    public S3Uploader(String profile, String region, String bucketName) throws IOException {
        Map<String, String> awsCredentials = AwsConfigReader.readAwsCredentials(profile);
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsCredentials.get("aws_access_key_id"), awsCredentials.get("aws_secret_access_key"));
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        this.bucketName = bucketName;
    }

    /**
     * Uploads all files in a directory to the S3 bucket.
     * @param directoryPath The path of the directory.
     */
    public void uploadDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                uploadFile(file);
            }
        }
    }

    /**
     * Uploads a file to the S3 bucket.
     * This method uses the Amazon S3 client to put the file into the bucket.
     * If an AmazonServiceException occurs, it will print the error message.
     *
     * @param file The file to be uploaded.
     */
    private void uploadFile(File file) {
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, file.getName(), file));
            System.out.println("Uploaded file: " + file.getName());
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }

    /**
     * Saves the game state to the S3 bucket.
     * @param player The player data.
     * @param building The building data.
     */
    public void saveGame(Player player, Building building){
        try {
            File directory = new File(System.getProperty("user.dir") + "/src/main/java/com/extraction/states");
            if (!directory.exists()){
                directory.mkdirs();
            }
            File[] files = directory.listFiles();

            // The new save number is one more than the max save number
            int newSaveNumber = getMaxSaveNumberFromS3();

            File file = new File(System.getProperty("user.dir") + "/src/main/java/com/extraction/states/save" + newSaveNumber + ".json");
            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            // Create a map to hold both Player and Building objects

            GameSave gameSave = new GameSave(player, building);


            // Convert the map to JSON and write it to the file
            gson.toJson(gameSave, writer);
            writer.flush();
            writer.close();
            uploadFile(file);
            file.delete();
            generateGameList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Downloads all games from the S3 bucket.
     */
    public void downloadAllGames() {
        String bucketName = "edidsgamesave";
        String localDirectoryPath = System.getProperty("user.dir") + "/src/main/java/com/extraction/states/";

        // Check if the directory exists, if not, create it
        File directory = new File(localDirectoryPath);
        if (!directory.exists()){
            directory.mkdirs();
        }

        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName);
        ListObjectsV2Result result;
        do {
            result = s3Client.listObjectsV2(req);
            if (result.getObjectSummaries().isEmpty()) {
                break;
            }

            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                String fileName = objectSummary.getKey();

                // Check if the file is a save file
                if (fileName.endsWith(".json")) {
                    S3Object s3object = s3Client.getObject(bucketName, fileName);
                    S3ObjectInputStream inputStream = s3object.getObjectContent();

                    // Save the file to the local directory
                    File localFile = new File(localDirectoryPath + fileName);
                    try (FileOutputStream fos = new FileOutputStream(localFile)) {
                        byte[] read_buf = new byte[1024];
                        int read_len = 0;
                        while ((read_len = inputStream.read(read_buf)) > 0) {
                            fos.write(read_buf, 0, read_len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            String token = result.getNextContinuationToken();
            req.setContinuationToken(token);
        } while (result.isTruncated());
    }

    /**
     * Gets the maximum save number from the S3 bucket.
     * @return The maximum save number.
     */
    public int getMaxSaveNumberFromS3() {
        String bucketName = "edidsgamesave";
        //String prefix = "com/extraction/states/save";
        int maxSaveNumber = 0;

        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName);//.withPrefix(prefix);
        ListObjectsV2Result result;

        do {
            result = s3Client.listObjectsV2(req);

            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                String fileName = objectSummary.getKey();

                if (fileName.startsWith("save") && fileName.endsWith(".json")) {
                    int saveNumber = Integer.parseInt(fileName.substring(4, fileName.length() - 5));

                    if (saveNumber > maxSaveNumber) {
                        maxSaveNumber = saveNumber;
                    }
                }
            }
            String token = result.getNextContinuationToken();
            req.setContinuationToken(token);
        } while (result.isTruncated());

        // The new save number is one more than the max save number
        int newSaveNumber = maxSaveNumber + 1;

        return newSaveNumber;
    }

    /**
     * Generates a list of games and uploads it to the S3 bucket.
     */
    private void generateGameList() {
        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName);
        ListObjectsV2Result result;
        List<String> gameList = new ArrayList<>();

        do {
            result = s3Client.listObjectsV2(req);

            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                String fileName = objectSummary.getKey();

                // Check if the file is a save file
                if (fileName.endsWith(".json")) {
                    gameList.add(fileName);
                }
            }

            String token = result.getNextContinuationToken();
            req.setContinuationToken(token);
        } while (result.isTruncated());

        deleteFile("gameList.json");

        // Write the game list to a JSON file
        File gameListFile = new File(System.getProperty("user.dir") + "/src/main/java/com/extraction/states/gameList.json");
        try (FileWriter writer = new FileWriter(gameListFile)) {
            gson.toJson(gameList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Upload the game list file to the S3 bucket
        uploadFile(gameListFile);

        // Delete the local game list file
        gameListFile.delete();
    }

    /**
     * Deletes a file from the S3 bucket.
     * @param fileName The name of the file to delete.
     */
    private void deleteFile(String fileName) {
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }
}