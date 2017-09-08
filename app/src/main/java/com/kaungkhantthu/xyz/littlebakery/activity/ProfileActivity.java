package com.kaungkhantthu.xyz.littlebakery.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kaungkhantthu.xyz.littlebakery.util.Constants;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.util.SPrefHelper;
import com.kaungkhantthu.xyz.littlebakery.api.RetrofitClient;
import com.kaungkhantthu.xyz.littlebakery.entity.User;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText ed_name;
    private EditText ed_address;
    private EditText ed_phnumber;
    private Button btn_save;
    private CallbackManager callbackManager;
    private String imgurl = "";
    private String TAG = ProfileActivity.class.getSimpleName();
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userid = SPrefHelper.getString(this, Constants.USERID,"0");

        init();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              boolean inputfilled =   isAllInputFilled();

                if(inputfilled == true) {

                    saveUserDataLocal();
                    if (userid.equals("0")) {
                    //new user
                        sendUserDataToserver(new User(ed_name.getText()+"",ed_phnumber.getText()+"",ed_address.getText()+"",imgurl));
                    }else{
                        //old user

                    }
                }else{
                    Toast.makeText(ProfileActivity.this, "Please fill All the field", Toast.LENGTH_SHORT).show();
                }
            }
        });
        initfacebooklogin();

    }

    private void saveUserDataLocal() {
        SPrefHelper.putString(ProfileActivity.this,Constants.NAME,ed_name.getText()+"");
        SPrefHelper.putString(ProfileActivity.this,Constants.PHNUM,ed_phnumber.getText()+"");
        SPrefHelper.putString(ProfileActivity.this,Constants.ADDRESS,ed_address.getText()+"");
    }

    private void sendUserDataToserver(User user) {
        RetrofitClient.getInstance().getService().PostUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    SPrefHelper.putString(getApplicationContext(),Constants.USERID,response.body().getId());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    private void init() {

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            ed_name =(EditText) findViewById(R.id.edtext_name);
            ed_address =(EditText) findViewById(R.id.edtext_address);
            ed_phnumber =(EditText) findViewById(R.id.edtext_phnumber);
            btn_save = (Button) findViewById(R.id.btn_save);

        String name = SPrefHelper.getString(this, Constants.NAME, "");
        String phnumber = SPrefHelper.getString(this, Constants.PHNUM, "");
        String address = SPrefHelper.getString(this, Constants.ADDRESS, "");
        ed_name.setText(name);
        ed_address.setText(address);
        ed_phnumber.setText(phnumber);

        setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void initfacebooklogin(){


        final ImageView img_profile = (ImageView) findViewById(R.id.profile_image);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email",
                "public_profile"));



        callbackManager = CallbackManager.Factory.create();
        final RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(new ColorDrawable(Color.parseColor("#EFEFEF")));
        requestOptions.error(new ColorDrawable(Color.parseColor("#000000")));

        if(Profile.getCurrentProfile() != null){
            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Profile.getCurrentProfile().getProfilePictureUri(100,100)+"")
                    .into(img_profile);
            imgurl = Profile.getCurrentProfile().getProfilePictureUri(100,100).toString();
        }



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e(TAG, "onSuccess: "+loginResult.getAccessToken().getUserId() );
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "onCancel: " );
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "onError: "+error );
            }
        });
    }


    public boolean isAllInputFilled() {
        boolean allInputFilled = false;
            if(ed_address.getText().toString().equals("") || ed_phnumber.getText().toString().equals("") || ed_name.getText().toString().equals("")){
                allInputFilled = false;
            }else{
                allInputFilled = true;
            }
        return allInputFilled;
    }
}
