package com.extraction.utils;

import com.extraction.map.Building;
import com.extraction.map.Room;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Map;

public class BuildingTypeAdapter implements JsonSerializer<Building>, JsonDeserializer<Building>{

    private final Gson roomGson;

    public BuildingTypeAdapter() {
        GsonBuilder roomGsonBuilder = new GsonBuilder();
        roomGsonBuilder.registerTypeAdapter(Room.class, new RoomTypeAdapter());
        this.roomGson = roomGsonBuilder.create();
    }

    @Override
    public JsonElement serialize(Building building, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonObject roomObject = new JsonObject();

        for (Map.Entry<String, Room> entry : building.getRoomsEntry().entrySet()){
            roomObject.add(entry.getKey(), roomGson.toJsonTree(entry.getValue(), Room.class));
        }

        jsonObject.add("building", roomObject);

        return jsonObject;
    }

    @Override
    public Building deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonObject roomObject = jsonObject.getAsJsonObject("building");

        Building building = new Building();

        for (Map.Entry<String, JsonElement> entry : roomObject.entrySet()){
            building.addRoom(roomGson.fromJson(entry.getValue(), Room.class));
        }

        return building;
    }
}
