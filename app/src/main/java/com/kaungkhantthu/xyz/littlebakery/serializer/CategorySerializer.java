package com.kaungkhantthu.xyz.littlebakery.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.xyz.littlebakery.entity.Category;

import java.lang.reflect.Type;

public class CategorySerializer implements JsonSerializer<Category> {
    @Override
    public JsonElement serialize(Category src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("_id", src.getId());
        jsonObject.addProperty("name", src.getName());



        return jsonObject;
    }
}