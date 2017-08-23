package com.kaungkhantthu.xyz.littlebakery.mvp.presenter;

import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;
import com.kaungkhantthu.xyz.littlebakery.entity.Category;
import com.kaungkhantthu.xyz.littlebakery.mvp.model.CakeModelImpl;
import com.kaungkhantthu.xyz.littlebakery.mvp.model.CategoryModel;
import com.kaungkhantthu.xyz.littlebakery.mvp.model.CategoryModelImpl;
import com.kaungkhantthu.xyz.littlebakery.mvp.view.CakeItemView;
import com.kaungkhantthu.xyz.littlebakery.mvp.view.CategoryItemView;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 8/21/17.
 */

public class CategoryPresenter {
    private CategoryModel categoryModel;

    private CategoryItemView categoryItemView;

    public CategoryPresenter(CategoryItemView c) {
        this.categoryItemView = c;
    }


    public void init() {
        this.categoryModel = CategoryModelImpl.getInstance();

        List<Category> categories = categoryModel.getCategoryFromCache();
        if (categories == null || categories.size() < 1) {
            categoryItemView.showErrorView();

        } else {
            categoryItemView.showCategorylist(categories);
        }
        requestCategory();

    }




    public void requestCategory() {
        categoryModel.getAllcategory(new CategoryModelImpl.Callback() {
            @Override
            public void onSuccess(RealmList<Category> categories) {
                categoryModel.saveCategory(categories);
                categoryItemView.showCategorylist(categories);
            }

            @Override
            public void onError() {


                //categoryItemView.showErrorView();
            }
        });

    }
}
