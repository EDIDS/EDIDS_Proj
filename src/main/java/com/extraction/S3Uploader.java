package com.extraction;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.extraction.map.Building;
import com.extraction.player.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S3Uploader {
    private String bucketName;
    private AmazonS3 s3Client;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public S3Uploader(String profile, String region, String bucketName) throws IOException {
        Map<String, String> awsCredentials = AwsConfigReader.readAwsCredentials(profile);
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsCredentials.get("aws_access_key_id"), awsCredentials.get("aws_secret_access_key"));
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        this.bucketName = bucketName;
    }

    public void uploadDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                uploadFile(file);
            }
        }
    }

    private void uploadFile(File file) {
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, file.getName(), file));
            System.out.println("Uploaded file: " + file.getName());
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }

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
            Map<String, Object> gameData = new HashMap<>();
            gameData.put("player", player);
            gameData.put("building", building);

            // Convert the map to JSON and write it to the file
            gson.toJson(gameData, writer);
            uploadFile(file);

            writer.close();
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                /*@TODO - stampa la mancata presenza di salvataggi a schermo*/
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

            // If there are more than maxKeys keys in the bucket, get a continuation token
            // and list the next objects.
            String token = result.getNextContinuationToken();
            req.setContinuationToken(token);
        } while (result.isTruncated());
    }

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

                // Check if the file is a save file
                if (fileName.endsWith(".json")) {
                    // Extract the save number from the file name
                    int saveNumber = Integer.parseInt(fileName.substring(4, fileName.length() - 5));

                    // Update the max save number if necessary
                    if (saveNumber > maxSaveNumber) {
                        maxSaveNumber = saveNumber;
                    }
                }
            }

            // If there are more than maxKeys keys in the bucket, get a continuation token
            // and list the next objects.
            String token = result.getNextContinuationToken();
            req.setContinuationToken(token);
        } while (result.isTruncated());

        // The new save number is one more than the max save number
        int newSaveNumber = maxSaveNumber + 1;

        return newSaveNumber;
    }
}