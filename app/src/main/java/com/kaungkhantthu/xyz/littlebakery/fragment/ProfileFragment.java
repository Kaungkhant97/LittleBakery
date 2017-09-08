package com.kaungkhantthu.xyz.littlebakery.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.kaungkhantthu.xyz.littlebakery.activity.HelpActivity;
import com.kaungkhantthu.xyz.littlebakery.activity.OrderHistoryActivity;
import com.kaungkhantthu.xyz.littlebakery.activity.ProfileActivity;
import com.kaungkhantthu.xyz.littlebakery.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#getInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {


    private static ProfileFragment fragment;
    private CallbackManager callbackManager;
    private ImageView img_profile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment getInstance() {
        if(fragment == null){
            fragment = new ProfileFragment();

        }
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_profile, container, false);
        img_profile = (ImageView) view.findViewById(R.id.profile_image);


        init(view);
        return view;
    }

    private void init(View v) {
        CardView profilecard = (CardView) v.findViewById(R.id.card_profile);
        CardView historycard = (CardView) v.findViewById(R.id.card_history);

        CardView helpcard = (CardView) v.findViewById(R.id.card_help);

        profilecard.setOnClickListener(this);
        historycard.setOnClickListener(this);
        helpcard.setOnClickListener(this);

        if(Profile.getCurrentProfile() != null){
            final RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(new ColorDrawable(Color.parseColor("#EFEFEF")));
            requestOptions.error(new ColorDrawable(Color.parseColor("#000000")));
            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Profile.getCurrentProfile().getProfilePictureUri(100,100)+"")
                    .into(img_profile);

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.card_profile:  Intent i1 = new Intent(getContext(),ProfileActivity.class);
                    startActivity(i1);
                break;

            case R.id.card_history:  Intent i2 = new Intent(getContext(),OrderHistoryActivity.class);
                startActivity(i2);
                break;
            case R.id.card_help:  Intent i4 = new Intent(getContext(),HelpActivity.class);
                startActivity(i4);
                break;

        }
    }

/*
    private void facebooklogin(View view){


        final ImageView img_profile = (ImageView) view.findViewById(R.id.profile_image);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email",
                "public_profile"));


        loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        final RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(new ColorDrawable(Color.parseColor("#EFEFEF")));
        requestOptions.error(new ColorDrawable(Color.parseColor("#000000")));

        if(Profile.getCurrentProfile() != null){
            Glide.with(getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(Profile.getCurrentProfile().getProfilePictureUri(100,100)+"")
                    .into(img_profile);
        }else{
            Glide.with(getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(R.drawable.ic_person_outline_black_24dp)
                    .into(img_profile);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Profile.getCurrentProfile() != null){
                    Glide.with(getContext())
                            .setDefaultRequestOptions(requestOptions)
                            .load(Profile.getCurrentProfile().getProfilePictureUri(100,100)+"")
                            .into(img_profile);
                }else{
                    Glide.with(getContext())
                            .setDefaultRequestOptions(requestOptions)
                            .load(R.drawable.ic_person_outline_black_24dp)
                            .into(img_profile);
                }
            }
        });

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
*/
}
