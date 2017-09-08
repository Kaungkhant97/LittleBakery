package com.kaungkhantthu.xyz.littlebakery.util;

import android.view.MotionEvent;
import android.view.View;

public class TouchEffect implements View.OnTouchListener {

  /* (non-Javadoc)
   * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
   */
  @Override public boolean onTouch(View v, MotionEvent event) {

    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      v.setAlpha(0.7f);
    } else if (event.getAction() == MotionEvent.ACTION_UP
        || event.getAction() == MotionEvent.ACTION_CANCEL) {
      v.setAlpha(1f);
    }
    return false;
  }
}