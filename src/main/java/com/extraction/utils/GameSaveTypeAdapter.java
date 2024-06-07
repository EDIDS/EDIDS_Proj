package com.extraction.utils;

import com.extraction.GameSave;
import com.extraction.map.Building;
import com.extraction.player.Player;
import com.google.gson.*;

import java.lang.reflect.Type;

public class GameSaveTypeAdapter implements JsonSerializer<GameSave>, JsonDeserializer<GameSave> {
    private final Gson playerGson;
    private final Gson buildingGson;

    public GameSaveTypeAdapter() {
        GsonBuilder playerGsonBuilder = new GsonBuilder();
        playerGsonBuilder.registerTypeAdapter(Player.class, new PlayerTypeAdapter());
        this.playerGson = playerGsonBuilder.create();

        GsonBuilder buildingGsonBuilder = new GsonBuilder();
        buildingGsonBuilder.registerTypeAdapter(Building.class, new BuildingTypeAdapter());
        this.buildingGson = buildingGsonBuilder.create();
    }

    @Override
    public GameSave deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Building building = buildingGson.fromJson(jsonObject.get("building"), Building.class);
        Player player = playerGson.fromJson(jsonObject.get("player"), Player.class);

        return new GameSave(player, building);
    }

    @Override
    public JsonElement serialize(GameSave gameSave, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("building", buildingGson.toJsonTree(gameSave.getBuilding()));
        jsonObject.add("player", playerGson.toJsonTree(gameSave.getPlayer()));
        return jsonObject;
    }
}
