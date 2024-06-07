package com.stanga.demovulcu.utils;

import androidx.annotation.NonNull;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.stanga.demovulcu.alien.Alien;

import java.lang.reflect.Type;

public class AlienTypeAdapter implements JsonSerializer<Alien>, JsonDeserializer<Alien> {

    @Override
    public JsonElement serialize(@NonNull Alien alien, Type typeOfSrc, @NonNull JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", alien.getClass().getSimpleName());
        jsonObject.add("properties", context.serialize(alien));
        return jsonObject;
    }

    @Override
    public Alien deserialize(@NonNull JsonElement json, Type typeOfT, @NonNull JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement properties = jsonObject.get("properties");

        try {
            Class<? extends Alien> alienClass = getClassForType(type);
            return context.deserialize(properties, alienClass);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown element type: " + type, e);
        }
    }

    @NonNull
    private Class<? extends Alien> getClassForType(String type) throws ClassNotFoundException {
        String className = "com.stanga.demovulcu.alien." + type;
        return Class.forName(className).asSubclass(Alien.class);
    }
}