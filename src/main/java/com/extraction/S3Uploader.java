package com.extraction;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class S3Uploader {
    private String bucketName;
    private AmazonS3 s3Client;

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
}