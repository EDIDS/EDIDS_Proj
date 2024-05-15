package com.extraction;

import java.io.File;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Uploader {
    private String bucketName;
    private AmazonS3 s3Client;

    public S3Uploader(String accessKey, String secretKey, String region, String bucketName) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
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
