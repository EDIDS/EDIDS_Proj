package com.extraction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The AwsConfigReader class is responsible for reading AWS credentials from a configuration file.
 */
public class AwsConfigReader {

    /**
     * Reads AWS credentials from a configuration file.
     * The configuration file is located in the user's home directory under the .aws directory.
     * The method reads the file line by line and extracts the AWS access key ID and secret access key.
     * These credentials are stored in a map and returned.
     *
     * @param profile The profile for which to read the credentials.
     * @return A map containing the AWS access key ID and secret access key.
     * @throws IOException If an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.
     */
    public static Map<String, String> readAwsCredentials(String profile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.home"), ".aws", "config"));
        Map<String, String> credentials = new HashMap<>();
        boolean isProfileSection = false;

        for (String line : lines) {
            if (credentials.size() == 2)
                break;

            System.out.println("Reading line: " + line);
            if (line.startsWith("[profile " + profile + "]") || (profile.equals("default") && line.startsWith("[default]"))) {
                isProfileSection = true;
                System.out.println("Found profile section: " + profile);
            } else if (line.startsWith("[") && !line.startsWith("[profile " + profile + "]")) {
                isProfileSection = false;
            }
            if (isProfileSection) {
                if (line.contains("aws_access_key_id")) {
                    credentials.put("aws_access_key_id", line.split("=")[1].trim());
                    System.out.println("Found aws_access_key_id: " + credentials.get("aws_access_key_id"));
                } else if (line.contains("aws_secret_access_key")) {
                    credentials.put("aws_secret_access_key", line.split("=")[1].trim());
                }
            }
        }

        return credentials;
    }
}