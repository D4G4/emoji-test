package com.example.daksh.emojitest.emoji.listeners;

import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by daksh
 */
public final class RepeatListener implements View.OnTouchListener {
  final long normalInterval;
  private final long initialInterval;
  final View.OnClickListener clickListener;

  final Handler handler = new Handler();

  View downView;

  private final Runnable handlerRunnable = new Runnable() {
    @Override public void run() {
      if (downView != null) {
        handler.removeCallbacksAndMessages(downView);
        handler.postAtTime(this, downView,
            SystemClock.uptimeMillis() + normalInterval);
        clickListener.onClick(downView);
      }
    }
  };

  public RepeatListener(long initialInterval, long normalInterval,
      View.OnClickListener clickListener) {
    if (clickListener == null) {
      throw new IllegalArgumentException("null runnable");
    }

    if (initialInterval < 0 || normalInterval < 0) {
      throw new IllegalArgumentException("negative interval");
    }

    this.initialInterval = initialInterval;
    this.normalInterval = normalInterval;
    this.clickListener = clickListener;
  }

  @Override public boolean onTouch(View v, MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        handler.removeCallbacks(handlerRunnable);
        handler.postAtTime(handlerRunnable, downView, SystemClock.uptimeMillis() + initialInterval);
        downView = v;
        downView.setPressed(true);
        clickListener.onClick(downView);
        return true;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
      case MotionEvent.ACTION_OUTSIDE:
        handler.removeCallbacksAndMessages(downView);
        downView.setPressed(false);
        downView = null;
        return true;
      default:
        break;
    }
    return false;
  }
}
