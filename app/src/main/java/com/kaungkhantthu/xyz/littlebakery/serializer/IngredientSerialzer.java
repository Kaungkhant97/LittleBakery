package com.kaungkhantthu.xyz.littlebakery.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.xyz.littlebakery.entity.Ingredient;

import java.lang.reflect.Type;

import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public class IngredientSerialzer implements JsonSerializer<RealmList<Ingredient>>,JsonDeserializer<RealmList<Ingredient>> {
    @Override
    public JsonElement serialize(RealmList<Ingredient> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        for (Ingredient tag : src) {
            ja.add(context.serialize(tag.getName()));
        }
        return ja;
    }

    @Override
    public RealmList<Ingredient> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        RealmList<Ingredient> ingredients = new RealmList<>();
        JsonArray ja = json.getAsJsonArray();
        for (JsonElement je : ja) {
            String e = context.deserialize(je, String.class);
            ingredients.add(new Ingredient(e));
        }
        return ingredients;
    }



}
