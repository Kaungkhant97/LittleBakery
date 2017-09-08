package com.kaungkhantthu.xyz.littlebakery.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaungkhantthu.xyz.littlebakery.util.Constants;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;
import com.kaungkhantthu.xyz.littlebakery.entity.Ingredient;
import com.kaungkhantthu.xyz.littlebakery.entity.Nutrition;
import com.kaungkhantthu.xyz.littlebakery.entity.Orderitem;
import com.kaungkhantthu.xyz.littlebakery.mvp.model.CakeModelImpl;

import io.realm.Realm;
import io.realm.RealmResults;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private Toolbar toolbar;

    private CakeModelImpl cakemodel;
    private Cakeitem cakeitem;
    private int quantity;
    private Realm realm;
    private boolean isOrder;
    private Orderitem orderitem;
    private FloatingActionButton fab;
    private TextView txt_name;

    private TextView txt_price;
    private TextView txt_ingredient;
    private TextView txt_description;
    private TextView txt_nutrioncalories;
    private TextView txt_nutrionfat;
    private TextView txt_nutrionsugar;
    private TextView txt_nutrionprotein;
    private TextView txt_nutrioncholestrol;
    private ImageView img_cake;
    private TextView txtnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        final String  id = getIntent().getStringExtra(Constants.CAKEID);
        cakemodel = new CakeModelImpl();
        realm = Realm.getDefaultInstance();
        orderitem = realm.where(Orderitem.class).equalTo("cakeId",id).equalTo("isOrdered",false).findFirst();
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
                   orderitem.setCakeImgurl(cakeitem.getImgurl());
                   orderitem.setCakeName(cakeitem.getName());
                   orderitem.setPrice(cakeitem.getPrice());
                   orderitem.setPrice(cakeitem.getPrice());
                    orderitem.setOrdered(false);
                   orderitem.setQuantity(0); //default is 0

                realm.copyToRealm(orderitem);

               }


                quantity=orderitem.getQuantity();
              orderitem.setQuantity(++quantity);

            updateCart();
                realm.commitTransaction();

            }
        });

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(new ColorDrawable(Color.parseColor("#EFEFEF")));
        requestOptions.error(new ColorDrawable(Color.parseColor("#000000")));

        Log.e(TAG, "onCreate: "+cakeitem.getImgurl());
        Glide.with(DetailActivity.this)
                .setDefaultRequestOptions(requestOptions)
                .load(cakeitem.getImgurl())

                .into(img_cake);


        txt_name.setText(cakeitem.getName());
        txt_description.setText(cakeitem.getDescription());

        txt_price.setText(cakeitem.getPrice()+" kyat");
        String ingredients = "";
        for(Ingredient i:cakeitem.getIngredient()){
            ingredients += i.getName()+" , ";
        }
        txt_ingredient.setText(ingredients);
        Nutrition n = cakeitem.getNutrition();

        if(n.getCalories() != 0){
            txt_nutrioncalories.setText("Calories: "+n.getCalories()+" mg");
        }
        if(n.getFat() != 0){
            txt_nutrionfat.setText("Total Fat: "+n.getFat()+" mg");
        }
        if(n.getCholesterol() != 0){
            txt_nutrioncholestrol.setText("Cholestrol: "+n.getCholesterol()+" mg");
        }
        if(n.getSugar() != 0){
            txt_nutrionsugar.setText("Sugar: "+n.getSugar()+" mg");
        }
        if(n.getCalories() != 0){
            txt_nutrionprotein.setText("Protein: "+n.getProtein()+" mg");
        }





    }

    private void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab);


        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_description = (TextView) findViewById(R.id.txt_description);

        txt_price = (TextView) findViewById(R.id.txt_price);

        txt_ingredient = (TextView) findViewById(R.id.txt_ingredient);
        img_cake = (ImageView) findViewById(R.id.img_cake);
        txt_nutrioncalories = (TextView) findViewById(R.id.txt_nutritioncalories);
        txt_nutrionfat = (TextView) findViewById(R.id.txt_nutritionfat);
        txt_nutrioncholestrol = (TextView) findViewById(R.id.txt_nutritioncholestrol);
        txt_nutrionprotein = (TextView) findViewById(R.id.txt_nutritionprotein);
        txt_nutrionsugar = (TextView) findViewById(R.id.txt_nutritionsugar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    protected void onResume() {
        super.onResume();
        updateCart();
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
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.menu_cart){
            Intent i = new Intent(this,OrderActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}

