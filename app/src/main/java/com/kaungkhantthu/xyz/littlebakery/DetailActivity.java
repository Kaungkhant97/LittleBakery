package com.kaungkhantthu.xyz.littlebakery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;
import com.kaungkhantthu.xyz.littlebakery.entity.Orderitem;
import com.kaungkhantthu.xyz.littlebakery.mvp.model.CakeModelImpl;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private CakeModelImpl cakemodel;
    private Cakeitem cakeitem;
    private int quantity;
    private Realm realm;
    private boolean isOrder;
    private Orderitem orderitem;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
         fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String  id = getIntent().getStringExtra(Constants.CAKEID);
        cakemodel = new CakeModelImpl();
        realm = Realm.getDefaultInstance();
        orderitem = realm.where(Orderitem.class).equalTo("cakeId",id).findFirst();
        cakeitem =cakemodel.getCakeByid(id);
           //check whether order item is valid or not


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
               if(orderitem == null){
                   //first time customer is adding this item
                   orderitem = realm.createObject(Orderitem.class);
                   orderitem.setCakeId(id);
                   orderitem.setCakeName(cakeitem.getName());
                   orderitem.setPrice(cakeitem.getPrice());

                   orderitem.setQuantity(0); //default is 0

                realm.copyToRealm(orderitem);

               }
                quantity=orderitem.getQuantity();
              orderitem.setQuantity(++quantity);


                realm.commitTransaction();
                Snackbar.make(view,"ur cake amount is "+ orderitem.getQuantity()+"", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activitymenu,menu);
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
}

