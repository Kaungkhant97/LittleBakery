package com.kaungkhantthu.xyz.littlebakery.mvp.presenter;

import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;
import com.kaungkhantthu.xyz.littlebakery.mvp.model.CakeModel;
import com.kaungkhantthu.xyz.littlebakery.mvp.model.CakeModelImpl;
import com.kaungkhantthu.xyz.littlebakery.mvp.view.CakeItemView;

import java.util.Calendar;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public class CakeitemPresenter {
    private CakeModel cakemodel;

  private  CakeItemView cakeItemView ;
    public CakeitemPresenter(CakeItemView c) {
        this.cakeItemView = c;
    }


    public void init() {
        this.cakemodel = CakeModelImpl.getInstance();

        List<Cakeitem> cakeList = cakemodel.getCakeFromCache();
        if (cakeList == null || cakeList.size() < 1) {
            cakeItemView.showErrorView();


        } else {
            cakeItemView.showCakelist(cakeList);

        }
        requestCakes();
    }




    public void requestCakes() {
        cakemodel.getAllCakeFromServer(new CakeModelImpl.Callback() {
            @Override
            public void onSuccess(RealmList<Cakeitem> cakeitems) {
                cakemodel.saveCake(cakeitems);
                cakeItemView.showCakelist(cakeitems);
            }

            @Override
            public void onError() {
                cakeItemView.showErrorView();
            }
        });

    }
}
