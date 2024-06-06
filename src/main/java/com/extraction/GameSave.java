package com.extraction;

import com.extraction.map.Building;
import com.extraction.player.PlayerData;

/**
 * The GameSave class represents a saved state of the game.
 * It contains the player's data, the building, and the title of the game.
 */
public class GameSave {
    Building building;
    PlayerData playerdata;
    String title;

    /**
     * Constructs a new GameSave with the given player data, building, and title.
     * @param playerdata The player's data.
     * @param building The building.
     * @param title The title of the game.
     */
    public GameSave(PlayerData playerdata, Building building, String title) {
        this.playerdata = playerdata;
        this.building = building;
        this.title = title;
    }

    /**
     * Gets the building.
     * @return The building.
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Gets the player's data.
     * @return The player's data.
     */
    public PlayerData getPlayer() {
        return playerdata;
    }

    /**
     * Gets the title of the game.
     * @return The title of the game.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the building.
     * @param building The new building.
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * Sets the player's data.
     * @param player The new player's data.
     */
    public void setPlayer(PlayerData player) {
        this.playerdata = player;
    }

    /**
     * Sets the title of the game.
     * @param title The new title of the game.
     */
    public void setTitle(String title) {
        this.title = title;
    }



}
