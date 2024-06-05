package com.extraction;

import com.extraction.map.Building;
import com.extraction.player.PlayerData;

public class GameSave {
    Building building;
    PlayerData playerdata;
    String title;

    public GameSave(PlayerData playerdata, Building building, String title) {
        this.playerdata = playerdata;
        this.building = building;
        this.title = title;
    }

    public Building getBuilding() {
        return building;
    }

    public PlayerData getPlayer() {
        return playerdata;
    }

    public String getTitle() {
        return title;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setPlayer(PlayerData player) {
        this.playerdata = player;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
