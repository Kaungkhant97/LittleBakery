package com.kaungkhantthu.xyz.littlebakery.mvp.model;

import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;
import com.kaungkhantthu.xyz.littlebakery.entity.Category;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 8/21/17.
 */

public interface CategoryModel {
    void saveCategory(RealmList<Category> categories);
    void getAllcategory(CategoryModelImpl.Callback c);
    void getCategoryByid(int id);
    void clearCategory();
    List<Category> getCategoryFromCache();
}
