package com.extraction.utils;

import com.extraction.items.Item;
import com.extraction.items.Shield;
import com.extraction.items.Weapon;
import com.extraction.map.Room;
import com.extraction.player.Player;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlayerTypeAdapter implements JsonSerializer<Player>, JsonDeserializer<Player>{

    private final Gson itemGson;
    private final Gson roomGson;

    public PlayerTypeAdapter() {
        GsonBuilder itemGsonBuilder = new GsonBuilder();
        itemGsonBuilder.registerTypeAdapter(Item.class, new ItemTypeAdapter());
        this.itemGson = itemGsonBuilder.create();

        GsonBuilder roomGsonBuilder = new GsonBuilder();
        roomGsonBuilder.registerTypeAdapter(Room.class, new RoomTypeAdapter());
        this.roomGson = roomGsonBuilder.create();
    }

    @Override
    public Player deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int health = jsonObject.get("health").getAsInt();
        String name = jsonObject.get("name").getAsString();
        List<Item> bag = new ArrayList<>();
        JsonArray bagArray = jsonObject.getAsJsonArray("bag");
        for (JsonElement item : bagArray){
            bag.add(itemGson.fromJson(item, Item.class));
        }
        double currentWeight = jsonObject.get("currentWeight").getAsDouble();
        Room currentRoom = roomGson.fromJson(jsonObject.get("currentRoom"), Room.class);
        int score = jsonObject.get("score").getAsInt();
        //andrebbe fatta una verifica ma ce ne sbattiamo

        Player player = new Player(name, health, currentRoom, null);
        player.setBag(bag);
        player.setCurrentWeight_(currentWeight);
        player.setScore(score);


        if (jsonObject.has("Weapon")){
            Weapon weapon = itemGson.fromJson(jsonObject.get("Weapon"), Weapon.class);
            player.setWeapon(weapon);
        }
        if (jsonObject.has("Shield")){
            Shield shield = itemGson.fromJson(jsonObject.get("Shield"), Shield.class);
            player.setShield(shield);
        }

        return player;
    }

    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("health", player.getHealth());
        jsonObject.addProperty("name", player.getName());
        jsonObject.addProperty("currentWeight", player.getCurrentWeight_());
        jsonObject.add("currentRoom", roomGson.toJsonTree(player.getCurrentRoom_()));
        jsonObject.addProperty("score", player.getScore());
        if (player.getWeapon() != null) {
            jsonObject.add("Weapon", itemGson.toJsonTree(player.getWeapon(), Weapon.class));
        }
        if (player.getShield() != null) {
            jsonObject.add("Shield", itemGson.toJsonTree(player.getShield(), Shield.class));
        }

        JsonArray bagArray = new JsonArray();
        for (Item item : player.getBag()){
            bagArray.add(itemGson.toJsonTree(item, Item.class));
        }
        jsonObject.add("bag", bagArray);

        return jsonObject;
    }
}
