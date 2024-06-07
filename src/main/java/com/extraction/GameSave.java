package com.extraction;

import com.extraction.map.Building;
import com.extraction.player.Player;

/**
 * The GameSave class represents a saved state of the game.
 * It contains the player's data, the building, and the title of the game.
 */
public class GameSave {
    Building building;
    Player playerdata;

    /**
     * Constructs a new GameSave with the given player data, building, and title.
     * @param playerdata The player's data.
     * @param building The building.
     */
    public GameSave(Player playerdata, Building building) {
        this.playerdata = playerdata;
        this.building = building;
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
    public Player getPlayer() {
        return playerdata;
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
    public void setPlayer(Player player) {
        this.playerdata = player;
    }
}
