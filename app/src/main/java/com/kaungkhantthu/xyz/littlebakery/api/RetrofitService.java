package com.kaungkhantthu.xyz.littlebakery.api;




import com.kaungkhantthu.xyz.littlebakery.entity.CakeitemResponse;
import com.kaungkhantthu.xyz.littlebakery.entity.CategoryResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by hhtet on 3/17/16.
 */
public interface RetrofitService {

  @GET(APIConfig.CAKE_LIST)
  Call<CakeitemResponse> getCakelist();

  @GET(APIConfig.CATEGORY_LIST)
  Call<CategoryResponse> getCategorylist();









}
