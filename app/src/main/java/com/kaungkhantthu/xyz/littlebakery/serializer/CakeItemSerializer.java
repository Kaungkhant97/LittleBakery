package com.kaungkhantthu.xyz.littlebakery.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;

import java.lang.reflect.Type;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public class CakeItemSerializer implements JsonSerializer<Cakeitem> {
    @Override
    public JsonElement serialize(Cakeitem src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("_id", src.getId());
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("imgurl", src.getImgurl());
        jsonObject.addProperty("catid", src.getCatid());
        jsonObject.addProperty("description", src.getDescription());
        jsonObject.add("nutrition",  context.serialize(src.getNutrition()));
        jsonObject.add("ingredient", context.serialize(src.getIngredient()));
        jsonObject.addProperty("price", src.getPrice());


        return jsonObject;
    }
}
