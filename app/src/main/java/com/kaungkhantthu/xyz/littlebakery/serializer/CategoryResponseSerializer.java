package com.kaungkhantthu.xyz.littlebakery.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.xyz.littlebakery.entity.CategoryResponse;

import java.lang.reflect.Type;

public class CategoryResponseSerializer implements JsonSerializer<CategoryResponse> {


    @Override
    public JsonElement serialize(CategoryResponse src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.add("data", context.serialize(src.getData()));
        return jsonObject;
    }
}