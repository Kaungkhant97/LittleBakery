package com.kaungkhantthu.xyz.littlebakery.mvp.view;

import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;

import java.util.List;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public interface CakeItemView {

    void showCakelist(List<Cakeitem> cakeItemList);


    void showErrorView();
    void showErrorView(String errorText);
}
