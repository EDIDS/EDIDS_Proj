package com.stanga.demovulcu.utils;

import androidx.annotation.NonNull;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.stanga.demovulcu.alien.Clicker;
import com.stanga.demovulcu.item.Item;

import java.lang.reflect.Type;

public class ItemTypeAdapter implements JsonSerializer<Item>, JsonDeserializer<Item> {

    @Override
    public JsonElement serialize(@NonNull Item item, Type typeOfItem, @NonNull JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", item.getClass().getSimpleName());
        jsonObject.add("properties", context.serialize(item));
        return jsonObject;
    }

    @Override
    public Item deserialize(@NonNull JsonElement json, Type typeOfT, @NonNull JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement properties = jsonObject.get("properties");

        try {
            Class<? extends Item> itemClass = getClassForType(type);
            return context.deserialize(properties, itemClass);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown element type: " + type, e);
        }
    }

    @NonNull
    private Class<? extends Item> getClassForType(String type) throws ClassNotFoundException {
        String className = "com.stanga.demovulcu.item." + type;
        return Class.forName(className).asSubclass(Item.class);
    }
}