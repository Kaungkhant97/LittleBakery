package com.kaungkhantthu.xyz.littlebakery.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.kaungkhantthu.xyz.littlebakery.R;

public class FBLoginDialog extends Dialog {

  Context context;
  String flag;
  onFBLoginListener mListener;

  public FBLoginDialog(Context context, String msg) {
    super(context);
    this.context = context;
    this.flag = msg;
    mListener = null;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.fb_dialog);
    View btnClose = findViewById(R.id.btnClose);
    btnClose.setOnTouchListener(new TouchEffect());
    btnClose.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
                /*SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor prefsEditor = sharedPrefs.edit();
                prefsEditor.putInt(Constants.KEY_LOGINALERT, 0).commit();*/
        dismiss();
      }
    });

    View btnFBLogin = findViewById(R.id.btnFacebookLogin);

    btnFBLogin.setOnTouchListener(new TouchEffect());
    btnFBLogin.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mListener != null) {
          mListener.onFBLoginClicked();
        }
        dismiss();
      }
    });
  }

  public void setOnFBLoginListener(onFBLoginListener listener) {
    mListener = listener;
  }

  public interface onFBLoginListener {
    void onFBLoginClicked();
  }
}