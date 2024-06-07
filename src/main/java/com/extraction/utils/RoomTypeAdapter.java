package com.extraction.utils;


import com.extraction.aliens.Alien;
import com.extraction.items.Item;
import com.extraction.map.Coordinate;
import com.extraction.map.Room;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class RoomTypeAdapter implements JsonSerializer<Room>, JsonDeserializer<Room> {

    private final Gson alienGson;
    private final Gson itemGson;

    public RoomTypeAdapter() {
        GsonBuilder alienGsonBuilder = new GsonBuilder();
        alienGsonBuilder.registerTypeAdapter(Alien.class, new AlienTypeAdapter());
        this.alienGson = alienGsonBuilder.create();

        GsonBuilder itemGsonBuilder = new GsonBuilder();
        itemGsonBuilder.registerTypeAdapter(Item.class, new ItemTypeAdapter());
        this.itemGson = itemGsonBuilder.create();
    }

    @Override
    public JsonElement serialize(Room room, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("coordinate", room.getCoordinate().toStringSimplified());
        jsonObject.addProperty("title", room.getTitle());
        jsonObject.addProperty("description", room.getDescription());
        jsonObject.addProperty("iconPath", room.getIconPath());
        jsonObject.addProperty("is_closed", room.isClosed());

        if (room.getAlien() != null) {
            jsonObject.add("alien", alienGson.toJsonTree(room.getAlien(), Alien.class));
        }

        JsonArray itemsArray = new JsonArray();
        room.getItems().forEach(item -> itemsArray.add(itemGson.toJsonTree(item, Item.class)));
        jsonObject.add("items", itemsArray);

        return jsonObject;
    }

    @Override
    public Room deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Coordinate coordinate = Coordinate.fromString(jsonObject.get("coordinate").getAsString());
        String title = jsonObject.get("title").getAsString();
        String description = jsonObject.get("description").getAsString();
        String iconPath = jsonObject.get("iconPath").getAsString();
        boolean isClosed = jsonObject.get("is_closed").getAsBoolean();

        Room room = new Room(title, coordinate, title, description);
        room.setIconPath(iconPath);
        if (isClosed) {
            room.close();
        } else {
            room.open();
        }

        if (jsonObject.has("alien")) {
            room.setAlien(alienGson.fromJson(jsonObject.get("alien"), Alien.class));
        }

        JsonArray itemsArray = jsonObject.getAsJsonArray("items");
        for (JsonElement itemElement : itemsArray) {
            Item item = itemGson.fromJson(itemElement, Item.class);
            room.addItem(item);
        }

        return room;
    }
}
