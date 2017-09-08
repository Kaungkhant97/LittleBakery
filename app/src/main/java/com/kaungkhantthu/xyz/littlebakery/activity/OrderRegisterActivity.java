package com.kaungkhantthu.xyz.littlebakery.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.kaungkhantthu.xyz.littlebakery.util.Constants;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.util.SPrefHelper;
import com.kaungkhantthu.xyz.littlebakery.api.RetrofitClient;
import com.kaungkhantthu.xyz.littlebakery.entity.OrderDetail;
import com.kaungkhantthu.xyz.littlebakery.entity.Orderitem;
import com.kaungkhantthu.xyz.littlebakery.entity.User;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRegisterActivity extends AppCompatActivity implements SingleDateAndTimePickerDialog.Listener, View.OnTouchListener {

    private Toolbar toolbar;
    private TextView txt_date;
    private EditText ed_phnumber;
    private EditText ed_address;
    private EditText ed_name;
    private SingleDateAndTimePickerDialog.Builder datepicker;
    private Date orderdate;
    private Button btn_confrim;
    private Realm realm;
    private RealmResults<Orderitem> realmorders;
    private String userid;
    private OrderDetail orderDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_register);
        init();
        initDatePicker();
        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker.display();
            }
        });


            btn_confrim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

            /*  OrderDetail orders =  getOrderDetail();
               */

                    if (isAllInputFilled()) {
                        AlertDialog alertDialog = new AlertDialog.Builder(OrderRegisterActivity.this)
                                .setTitle("Order Confirm")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        saveUserDataLocal();
                                        saveOrderDetail();
                                        if (userid.equals("0")) {
                                            //new user so neww

                                            sendUserDataToserver(new User(ed_name.getText() + "", ed_phnumber.getText() + "", ed_address.getText() + ""));
                                            navigatetoOrderhistory();

                                        }else{
                                            //Todo quit from this screen after sending request

                                            sendOrderDataToServer();
                                            navigatetoOrderhistory();

                                        }

                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })

                                .create();

                        alertDialog.show();
                    } else{
                        Toast.makeText(OrderRegisterActivity.this, "Please Filled All the fields", Toast.LENGTH_SHORT).show();
                    }

                }
            });


    }

    private void saveOrderDetail() {
         orderDetail = new OrderDetail();
        RealmList<Orderitem> orders = new RealmList<>();
        realmorders = realm.where(Orderitem.class).equalTo("isOrdered",false).findAll();
        orders.addAll(realmorders);
        orderDetail.setOrderitems(orders);
        orderDetail.setUserId(userid);
        orderDetail.setDeliveryAddress(ed_address.getText()+"");
        orderDetail.setDeliveryDate(orderdate.getTime());
        orderDetail.setName(ed_name.getText().toString());
        orderDetail.setPhnumber(ed_phnumber.getText().toString());


        realm.beginTransaction();
        realm.copyToRealm(orderDetail);
        for(Orderitem order:realmorders){
            order.setOrdered(true);
        }
        realmorders.deleteAllFromRealm();
        realm.commitTransaction();
    }

    private void navigatetoOrderhistory() {
        Intent intent = new Intent(getApplicationContext(),OrderHistoryActivity.class);
        startActivity(intent);
        OrderRegisterActivity.this.finish();
    }


    private void initDatePicker() {
        Date d = Calendar.getInstance().getTime();
        d.setDate(d.getDate()+2);
    this.datepicker =  new SingleDateAndTimePickerDialog.Builder(this)
                .bottomSheet()
                .curved()
                .defaultDate(d)
                .minDateRange(d)
                .title("Delivery Date")
                .listener(this);
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ed_name =(EditText) findViewById(R.id.edtext_name);
        ed_address =(EditText) findViewById(R.id.edtext_address);
        ed_phnumber =(EditText) findViewById(R.id.edtext_phnumber);
        btn_confrim = (Button) findViewById(R.id.btn_confrim);
        txt_date =(TextView) findViewById(R.id.txt_date);
        realm = Realm.getDefaultInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);;
         userid = SPrefHelper.getString(this, Constants.USERID, "0");

        String name = SPrefHelper.getString(this, Constants.NAME, "");
        String phnumber = SPrefHelper.getString(this, Constants.PHNUM, "");
        String address = SPrefHelper.getString(this, Constants.ADDRESS, "");

        if(userid != "0"){
            ed_name.setVisibility(View.GONE);
            ed_phnumber.setVisibility(View.GONE);
        }
        ed_name.setText(name);
        ed_phnumber.setText(phnumber);

        ed_address.setText(address);


        ed_name.setOnTouchListener(this);
        ed_address.setOnTouchListener(this);
        ed_phnumber.setOnTouchListener(this);

    }

    @Override
    public void onDateSelected(Date date) {

        this.orderdate = date;
        txt_date.setText(date.toLocaleString());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
    public boolean isAllInputFilled() {
        boolean allInputFilled = false;
        if(ed_address.getText().toString().equals("") || ed_phnumber.getText().toString().equals("")
                || ed_name.getText().toString().equals("") || txt_date.getText().toString().equals("Delivery Date")){
            allInputFilled = false;
        }else{
            allInputFilled = true;
        }

        return allInputFilled;
    }

    private void sendUserDataToserver(User user) {
        RetrofitClient.getInstance().getService().PostUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    SPrefHelper.putString(getApplicationContext(),Constants.USERID,response.body().getId());
                    userid = response.body().getId();
                    sendOrderDataToServer();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    private void saveUserDataLocal() {
        SPrefHelper.putString(this,Constants.NAME,ed_name.getText()+"");
        SPrefHelper.putString(this,Constants.PHNUM,ed_phnumber.getText()+"");
        SPrefHelper.putString(this,Constants.ADDRESS,ed_address.getText()+"");
    }

    private void sendOrderDataToServer() {

        RetrofitClient.getInstance().getService().PostOrder(orderDetail).enqueue(new Callback<OrderDetail>() {
            @Override
            public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {


            }

            @Override
            public void onFailure(Call<OrderDetail> call, Throwable t) {


            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {


        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;
        if (view instanceof EditText){
            EditText editText = (EditText) view;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // your action here
                        editText.setText("");
                    return true;
                }
            }
    }
        return false;
    }
}
