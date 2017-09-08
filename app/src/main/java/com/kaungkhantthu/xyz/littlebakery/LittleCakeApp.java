package com.kaungkhantthu.xyz.littlebakery;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by kaungkhantthu on 8/20/17.
 */

public class LittleCakeApp extends Application {

    private RealmMigration realmMigration;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        Realm.init(this);
       /* setuprealmMigration();
        RealmConfiguration conf = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .migration(realmMigration)
                .build();
       */
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        //FireBase and Stetho
        if(BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build());
        }

    }


    private void setuprealmMigration(){

        realmMigration = new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                RealmSchema schema = realm.getSchema();

                Log.e( "migrate: ","old "+oldVersion+" "+" new"+ newVersion );
            }
        };

    }

    public static Context getContext() {
        return context;
    }
}
