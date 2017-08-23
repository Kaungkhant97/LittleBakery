package com.kaungkhantthu.xyz.littlebakery.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.xyz.littlebakery.entity.CakeitemResponse;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public class CakeItemResponseSerializer implements JsonSerializer<CakeitemResponse> {


    @Override
    public JsonElement serialize(CakeitemResponse src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.add("data", context.serialize(src.getData()));
        return jsonObject;
    }
}
