package com.extraction.map;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import com.extraction.player.Player;
import com.google.gson.GsonBuilder;

/**
 * Classe che rappresenta un salvataggio di gioco. Salviamo il gioco in JSON, tutte le classi che vogliamo salvare devono avere getter e setter
 * Servirà appunto per prendere i dati e salvarli in un file JSON e poi per caricarli.
 */

public class GameSave {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void saveGame(Player player, Building building){
        try {
            File directory = new File("src/states/");
            File[] files = directory.listFiles();
            int maxSaveNumber = 0;

            // Iterate over all files in the directory
            for (File file : files) {
                String fileName = file.getName();

                // Check if the file is a save file
                if (fileName.startsWith("save") && fileName.endsWith(".json")) {
                    // Extract the save number from the file name
                    int saveNumber = Integer.parseInt(fileName.substring(4, fileName.length() - 5));

                    // Update the max save number if necessary
                    if (saveNumber > maxSaveNumber) {
                        maxSaveNumber = saveNumber;
                    }
                }
            }

            // The new save number is one more than the max save number
            int newSaveNumber = maxSaveNumber + 1;

            File file = new File("src/states/save" + newSaveNumber + ".json");
            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            // Create a map to hold both Player and Building objects
            Map<String, Object> gameData = new HashMap<>();
            gameData.put("player", player);
            gameData.put("building", building);

            // Convert the map to JSON and write it to the file
            gson.toJson(gameData, writer);

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}