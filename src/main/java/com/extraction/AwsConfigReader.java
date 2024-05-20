package com.extraction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AwsConfigReader {
    public static Map<String, String> readAwsCredentials(String profile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.home"), ".aws", "config"));
        Map<String, String> credentials = new HashMap<>();
        boolean isProfileSection = false;

        for (String line : lines) {
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