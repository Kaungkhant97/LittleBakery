package com.kaungkhantthu.xyz.littlebakery.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kaungkhantthu.xyz.littlebakery.util.FBLoginDialog;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.OrderDetail;
import com.kaungkhantthu.xyz.littlebakery.entity.Orderitem;
import com.kaungkhantthu.xyz.littlebakery.recyclerView.OrderRecyclerAdapter;

import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class OrderActivity extends AppCompatActivity implements FBLoginDialog.onFBLoginListener, OrderRecyclerAdapter.AmountchangeListner {

    private Toolbar toolbar;
    private Realm realm;
    private RecyclerView recyclerview;
    private OrderRecyclerAdapter adapter;
    private RealmResults<Orderitem> orderitems;
    private Button orderButton;
    private CallbackManager callbackManager;
    private TextView txt_totalquantity;
    private TextView txt_totalprice;
    private int totalprice;
    private int totalquantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
        initfacebook();
        orderitems = realm.where(Orderitem.class).equalTo("isOrdered",false).findAll();
        totalprice = gettotalprice();
        totalquantity = gettotalquantity();
        setTotalamounts();
        initRecycler();

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                if( AccessToken.getCurrentAccessToken() ==null) {
                    FBLoginDialog dialog = new FBLoginDialog(OrderActivity.this, "sdsdsdsd");
                    dialog.setOnFBLoginListener(OrderActivity.this);
                    dialog.show();
                }else{
                    Profile profile = Profile.getCurrentProfile();

                    Toast.makeText(OrderActivity.this,profile.getName()+profile.getLinkUri()+"", Toast.LENGTH_SHORT).show();
                    sendDataToServer();
                }*/
if(orderitems.size()<=0){
    Toast.makeText(OrderActivity.this, "Please Add Cake Items into orderlist make an Order", Toast.LENGTH_SHORT).show();
}else{
    Intent i = new Intent(OrderActivity.this,OrderRegisterActivity.class);
    startActivity(i);
}


            }
        });



    }

    private void setTotalamounts() {

        txt_totalprice.setText(totalprice+"");
        txt_totalquantity.setText(totalquantity+"");
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initfacebook() {
        callbackManager = CallbackManager.Factory.create();
    }

    private void initRecycler() {

        adapter = new OrderRecyclerAdapter(orderitems, this, this);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerview.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerview.addItemDecoration(mDividerItemDecoration);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
    }

    private void init() {
        recyclerview = (RecyclerView) findViewById(R.id.order_recycler);

        orderButton = (Button) findViewById(R.id.btn_placeOrder);
        txt_totalquantity = (TextView) findViewById(R.id.txt_totalQuantity);
        txt_totalprice = (TextView) findViewById(R.id.txt_totalPrice);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onFBLoginClicked() {

        LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("email",
                        "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    public OrderDetail getOrderDetail() {
        OrderDetail orderDetail = new OrderDetail();

        RealmList<Orderitem> orders = new RealmList<>();
        orders.addAll(adapter.getOrder());
        orderDetail.setOrderitems(orders);
        orderDetail.setUserId(Profile.getCurrentProfile().getId());
        orderDetail.setDeliveryAddress("12/33,dnsj/sdnsjcnscs");


        return orderDetail;
    }

    @Override
    public void amountincrease(int quantity, int price) {
        totalprice = totalprice+ price ;
        totalquantity = totalquantity+ quantity;
        setTotalamounts();
    }

    @Override
    public void amountdecrease(int quantity, int price) {
        totalprice = totalprice -price ;
        totalquantity = totalquantity - quantity;
        setTotalamounts();
    }

    public int gettotalprice() {
        int totalprice = 0;
        for(Orderitem item :orderitems){
          totalprice +=  item.getTotalprice();
        }
        return totalprice;
    }
    public int gettotalquantity() {
        int totalquantity = 0;
        for(Orderitem item :orderitems){
            totalquantity +=  item.getQuantity();
        }
        return totalquantity;
    }

    @Override
    protected void onResume() {
       adapter.notifyDataSetChanged();
        totalprice = gettotalprice();
        totalquantity = gettotalquantity();
        setTotalamounts();
        super.onResume();
    }
}
