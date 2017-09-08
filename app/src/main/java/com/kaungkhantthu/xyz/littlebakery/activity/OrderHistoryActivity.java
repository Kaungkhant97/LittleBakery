package com.kaungkhantthu.xyz.littlebakery.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.OrderDetail;

import io.realm.Realm;
import io.realm.RealmResults;

public class OrderHistoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Realm realm;
    private RealmResults<OrderDetail> orderHistories;
    private OrderHistoryRecyclerAdapter adapter;
    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        init();
        orderHistories = realm.where(OrderDetail.class).findAll();
        adapter = new OrderHistoryRecyclerAdapter(orderHistories);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recycler_view =(RecyclerView) findViewById(R.id.recycler_item);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);;
        realm = Realm.getDefaultInstance();
    }
}
