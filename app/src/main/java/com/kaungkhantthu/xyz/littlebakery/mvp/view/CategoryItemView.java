package com.kaungkhantthu.xyz.littlebakery.mvp.view;

import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;
import com.kaungkhantthu.xyz.littlebakery.entity.Category;

import java.util.List;

/**
 * Created by kaungkhantthu on 8/21/17.
 */


    public interface CategoryItemView {

        void showCategorylist(List<Category> categories);


        void showErrorView();
        void showErrorView(String errorText);

    }

