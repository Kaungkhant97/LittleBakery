package com.kaungkhantthu.xyz.littlebakery.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.kaungkhantthu.xyz.littlebakery.fragment.ProfileFragment;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.Orderitem;
import com.kaungkhantthu.xyz.littlebakery.fragment.CakeItemFragment;
import com.kaungkhantthu.xyz.littlebakery.fragment.CategoryFragment;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity   {

    private static final String TAG = "MAINACTIVITY";
    private TextView mTextMessage;
    private FrameLayout container;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    getSupportFragmentManager().beginTransaction().replace(container.getId(), CakeItemFragment.newInstance()).commit();

                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(container.getId(), CategoryFragment.newInstance()).commit();
                    return true;
                case R.id.navigation_notifications:

                    getSupportFragmentManager().beginTransaction().replace(container.getId(), ProfileFragment.getInstance()).commit();

                    return true;
            }
            return false;
        }

    };
    private Toolbar toolbar;
    private CallbackManager callbackManager;
    private Realm realm = Realm.getDefaultInstance();
    private TextView txtnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (FrameLayout) findViewById(R.id.content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        callbackManager = CallbackManager.Factory.create();

        getSupportFragmentManager().beginTransaction().add(container.getId(), CakeItemFragment.newInstance()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activitymenu,menu);

        final MenuItem menu_cart = menu.findItem(R.id.menu_cart);
        FrameLayout layout =  menu.findItem(R.id.menu_cart).getActionView().findViewById(R.id.actionview);


        txtnumber =  menu.findItem(R.id.menu_cart).getActionView().findViewById(R.id.count_txt_pin);
        ImageView cartImg =  menu.findItem(R.id.menu_cart).getActionView().findViewById(R.id.img_cart);

        float x= cartImg.getTranslationX();
        float y= cartImg.getTranslationX();
        txtnumber.setTranslationX(x+ cartImg.getWidth()+28);
        txtnumber.setTranslationY(y+cartImg.getHeight()+20);


        updateCart();
        layout.bringToFront();
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(menu_cart);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.menu_cart){
            Intent i = new Intent(this,OrderActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateCart();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
    private void updateCart() {
        RealmResults<Orderitem> result = realm.where(Orderitem.class).equalTo("isOrdered",false).findAll();
        int amount = 0;
        for(Orderitem item:result){
            amount +=  item.getQuantity();
        }
        if(txtnumber != null){
            txtnumber.setText(amount+"");
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
