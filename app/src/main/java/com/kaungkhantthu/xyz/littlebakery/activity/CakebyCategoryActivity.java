package com.kaungkhantthu.xyz.littlebakery.activity;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;
import com.kaungkhantthu.xyz.littlebakery.fragment.CakeItemFragment;
import com.kaungkhantthu.xyz.littlebakery.fragment.CategoryFragment;
import com.kaungkhantthu.xyz.littlebakery.recyclerView.CakeItemRecyclerViewAdapter;
import com.kaungkhantthu.xyz.littlebakery.util.Constants;
import com.kaungkhantthu.xyz.littlebakery.util.OnListFragmentInteractionListener;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class CakebyCategoryActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    private Toolbar toolbar;
    private RecyclerView recyler_cake;
    private Realm realm;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakeby_category);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         id = getIntent().getIntExtra(Constants.CATID,1);

        initRecycler();

    }

    private void initRecycler() {
        recyler_cake = (RecyclerView) findViewById(R.id.recycler_item);
        recyler_cake.setLayoutManager(new GridLayoutManager(this,1));
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyler_cake.getContext(),
                DividerItemDecoration.VERTICAL);
        recyler_cake.addItemDecoration(mDividerItemDecoration);
         realm = Realm.getDefaultInstance();
        ArrayList<Cakeitem> mycakelist = new ArrayList<>();
        RealmResults<Cakeitem> realmlist = realm.where(Cakeitem.class).equalTo("catid", id).findAll();
        mycakelist.addAll(realmlist);
        if(mycakelist.size() <= 0){
            Toast.makeText(this,"There is no item in this category",Toast.LENGTH_LONG).show();
        }
        CakeItemRecyclerViewAdapter adapter = new CakeItemRecyclerViewAdapter(mycakelist, this, this);
        recyler_cake.setAdapter(adapter);


    }



    @Override
    public void onListItemClick(Object item, View v) {
        Cakeitem c = (Cakeitem)item;
        Toast.makeText(this, ""+c.getName(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,DetailActivity.class);
        i.putExtra(Constants.CAKEID,c.getId());


        startActivity(i);
    }
}
