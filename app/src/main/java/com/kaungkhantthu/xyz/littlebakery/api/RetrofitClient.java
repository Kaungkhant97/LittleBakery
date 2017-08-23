package com.kaungkhantthu.xyz.littlebakery.api;

import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kaungkhantthu.xyz.littlebakery.entity.Ingredient;
import com.kaungkhantthu.xyz.littlebakery.serializer.CakeItemResponseSerializer;
import com.kaungkhantthu.xyz.littlebakery.serializer.CakeItemSerializer;
import com.kaungkhantthu.xyz.littlebakery.serializer.CategoryResponseSerializer;
import com.kaungkhantthu.xyz.littlebakery.serializer.CategorySerializer;
import com.kaungkhantthu.xyz.littlebakery.serializer.IngredientSerialzer;
import com.kaungkhantthu.xyz.littlebakery.serializer.NutritionSerializer;


import java.util.concurrent.TimeUnit;


import io.realm.RealmList;
import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {


    private static String URL = APIConfig.BASE_URL;
    private static RetrofitClient mInstance;
    private RetrofitService mService;

    private RetrofitClient() {

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient httpClient;


        httpClient = new OkHttpClient().newBuilder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();


        //this code is to work both Realm and Gson
        Gson gson = null;
        try {
            gson = new GsonBuilder()
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getDeclaringClass().equals(RealmObject.class);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }

                    }).registerTypeAdapter(Class.forName("io.realm.CakeitemRealmProxy"), new CakeItemResponseSerializer())

                    .registerTypeAdapter(Class.forName("io.realm.CakeitemResponseRealmProxy"), new CakeItemSerializer())
                    .registerTypeAdapter(Class.forName("io.realm.CategoryResponseRealmProxy"), new CategoryResponseSerializer())
                    .registerTypeAdapter(Class.forName("io.realm.CategoryRealmProxy"), new CategorySerializer())

                    .registerTypeAdapter(Class.forName("io.realm.NutritionRealmProxy"), new NutritionSerializer())
                    .registerTypeAdapter(new TypeToken<RealmList<Ingredient>>() {}.getType(),new IngredientSerialzer())
                    .create();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e( "RetrofitClient: ",e.getMessage() );
        }


        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        mService = retrofit.create(RetrofitService.class);


    }

    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public RetrofitService getService() {
        return mService;
    }

}