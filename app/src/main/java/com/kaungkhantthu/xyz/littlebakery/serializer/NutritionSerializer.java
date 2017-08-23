package com.kaungkhantthu.xyz.littlebakery.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.xyz.littlebakery.entity.Nutrition;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public class NutritionSerializer implements JsonSerializer<Nutrition> {
    @Override
    public JsonElement serialize(Nutrition src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("_id", src.getId());
        jsonObject.addProperty("calories", src.getCalories());
        jsonObject.addProperty("fat", src.getFat());
        jsonObject.addProperty("cholesterol", src.getCholesterol());
        jsonObject.addProperty("sugar", src.getSugar());
        jsonObject.addProperty("protein", src.getProtein());

        return jsonObject;
    }
}
