package com.extraction;

import com.extraction.map.Building;
import com.extraction.player.Player;

public class GameSave {
    Building building;
    Player playerdata;

    public GameSave(Player playerdata, Building building) {
        this.playerdata = playerdata;
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }

    public Player getPlayer() {
        return playerdata;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setPlayer(Player player) {
        this.playerdata = player;
    }
}