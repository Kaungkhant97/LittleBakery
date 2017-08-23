package com.kaungkhantthu.xyz.littlebakery.mvp.model;

import com.kaungkhantthu.xyz.littlebakery.api.RetrofitClient;
import com.kaungkhantthu.xyz.littlebakery.entity.CakeitemResponse;
import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public class CakeModelImpl implements CakeModel {
    private static CakeModelImpl cakeModel;
    private  Realm realm = Realm.getDefaultInstance();;


    public static CakeModel getInstance() {
        if (cakeModel == null) {
            cakeModel = new CakeModelImpl();



        }
        return cakeModel;
    }
    @Override
    public void saveCake(RealmList<Cakeitem> cake) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(cake);
        realm.commitTransaction();
    }


    @Override
    public void getAllCakeFromServer(final CakeModelImpl.Callback c) {
        RetrofitClient.getInstance().getService().getCakelist().enqueue(new retrofit2.Callback<CakeitemResponse>() {
            @Override
            public void onResponse(Call<CakeitemResponse> call, Response<CakeitemResponse> response) {
                if (response.body() != null) {
                    //TODO /**subject list could be empty**/
                    c.onSuccess(response.body().getData());

                }else{
                    c.onError();
                }
            }

            @Override
            public void onFailure(Call<CakeitemResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public Cakeitem getCakeByid(String id) {
       Cakeitem c = realm.where(Cakeitem.class).equalTo("id",id).findFirst();

        return c;
    }

    @Override
    public void clearCake() {
        realm.beginTransaction();
        realm.where(Cakeitem.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    @Override
    public List<Cakeitem> getCakeFromCache() {
        realm.beginTransaction();
        RealmResults<Cakeitem> result = realm.where(Cakeitem.class).findAll();

        List<Cakeitem> cakeitemList= realm.copyFromRealm(result);
        realm.commitTransaction();
        return cakeitemList;
    }

    public interface Callback {
        void onSuccess(RealmList<Cakeitem> cakeitems);

        void onError();
    }
}
