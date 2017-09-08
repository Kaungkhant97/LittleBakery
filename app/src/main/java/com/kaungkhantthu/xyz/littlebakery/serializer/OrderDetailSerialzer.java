package com.kaungkhantthu.xyz.littlebakery.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.xyz.littlebakery.entity.CategoryResponse;
import com.kaungkhantthu.xyz.littlebakery.entity.OrderDetail;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 8/23/17.
 */

public class OrderDetailSerialzer implements JsonSerializer<OrderDetail> {
    @Override
    public JsonElement serialize(OrderDetail src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.add("orderitem", context.serialize(src.getOrderitems()));
        jsonObject.add("userId", context.serialize(src.getUserId()));
        jsonObject.add("deliveryaddress", context.serialize(src.getDeliveryAddress()));
        jsonObject.add("deliveryDate", context.serialize(src.getDeliveryDate()));
        jsonObject.add("name", context.serialize(src.getName()));
        jsonObject.add("phnumber", context.serialize(src.getPhnumber()));

        return jsonObject;
    }
}
