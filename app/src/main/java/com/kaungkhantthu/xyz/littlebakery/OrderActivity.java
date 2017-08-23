package com.kaungkhantthu.xyz.littlebakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kaungkhantthu.xyz.littlebakery.entity.Orderitem;
import com.kaungkhantthu.xyz.littlebakery.recyclerView.OrderRecyclerAdapter;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class OrderActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Realm realm;
    private RecyclerView recyclerview;
    private OrderRecyclerAdapter adapter;
    private RealmResults<Orderitem> orderitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
        orderitems = realm.where(Orderitem.class).findAll();
        initRecycler();




    }

    private void initRecycler() {
        adapter = new OrderRecyclerAdapter(orderitems,this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
    }

    private void init() {
        recyclerview = (RecyclerView) findViewById(R.id.order_recycler);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);;
        realm = Realm.getDefaultInstance();
    }
}
