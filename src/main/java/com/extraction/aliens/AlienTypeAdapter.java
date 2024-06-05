package com.extraction.aliens;

import com.google.gson.*;

import java.lang.reflect.Type;

public class AlienTypeAdapter implements JsonSerializer<Alien>, JsonDeserializer<Alien> {


    @Override
    public JsonElement serialize(Alien src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getSimpleName());
        jsonObject.add("properties", context.serialize(src));
        return jsonObject;
    }

    @Override
    public Alien deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement properties = jsonObject.get("properties");

        try {
            if (type.equals("Clicker")) {
                return context.deserialize(properties, Clicker.class);
            } else if (type.equals("Runner")) {
                return context.deserialize(properties, Runner.class);
            } else if (type.equals("Shambler")) {
                return context.deserialize(properties, Shambler.class);
            } else {
                throw new JsonParseException("Unknown element type: " + type);
            }
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
