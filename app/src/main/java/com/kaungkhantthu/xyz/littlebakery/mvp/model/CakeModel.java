package com.kaungkhantthu.xyz.littlebakery.mvp.model;

import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public interface CakeModel {
    void saveCake(RealmList<Cakeitem> Subject);
    void getAllCakeFromServer(CakeModelImpl.Callback c);
    Cakeitem getCakeByid(String id);
    void clearCake();
    List<Cakeitem> getCakeFromCache();

}
