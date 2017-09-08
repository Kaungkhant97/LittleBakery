package com.kaungkhantthu.xyz.littlebakery.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.xyz.littlebakery.entity.OrderDetail;
import com.kaungkhantthu.xyz.littlebakery.entity.Orderitem;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 8/23/17.
 */

public class OrderitemSerializer implements JsonSerializer<Orderitem> {
    @Override
    public JsonElement serialize(Orderitem src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.add("cakeid", context.serialize(src.getCakeId()));
        jsonObject.add("amount", context.serialize(src.getQuantity()));


        return jsonObject;
    }
}