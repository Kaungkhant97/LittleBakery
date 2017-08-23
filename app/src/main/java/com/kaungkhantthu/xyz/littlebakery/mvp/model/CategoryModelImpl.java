package com.kaungkhantthu.xyz.littlebakery.mvp.model;

import com.kaungkhantthu.xyz.littlebakery.api.RetrofitClient;

import com.kaungkhantthu.xyz.littlebakery.entity.Category;
import com.kaungkhantthu.xyz.littlebakery.entity.CategoryResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kaungkhantthu on 8/21/17.
 */

public class CategoryModelImpl implements CategoryModel{


    private static CategoryModelImpl categoryModel;
    private Realm realm = Realm.getDefaultInstance();;


    public static CategoryModel getInstance() {
        if (categoryModel == null) {
            categoryModel = new CategoryModelImpl();



        }
        return categoryModel;
    }
    @Override
    public void saveCategory(RealmList<Category> categories) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(categories);
        realm.commitTransaction();
    }

    @Override
    public void getAllcategory(final Callback c) {
        RetrofitClient.getInstance().getService().getCategorylist().enqueue(new retrofit2.Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    c.onSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                c.onError();
            }
        });
    }

    @Override
    public void getCategoryByid(int id) {

    }

    @Override
    public void clearCategory() {
        realm.beginTransaction();
        realm.where(Category.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    @Override
    public List<Category> getCategoryFromCache() {
        realm.beginTransaction();
        RealmResults<Category> result = realm.where(Category.class).findAll();

        List<Category> categories= realm.copyFromRealm(result);
        realm.commitTransaction();
        return categories;
    }

    public interface Callback {
        void onSuccess(RealmList<Category> categories);

        void onError();
    }
}
