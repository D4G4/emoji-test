package com.example.daksh.emojitest.emoji.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;

/**
 * Created by daksh
 */
public class Utils {
  static final int DONT_UPDATE_FLAG = -1;

  @NonNull public static <T> T checkNotNull(@Nullable final T reference, final String message) {
    if (reference == null) {
      throw new IllegalArgumentException(message);
    }
    return reference;
  }

  public static int dpToPx(@NonNull final Context context, final float dp) {
    return (int) (dp * context.getResources().getDisplayMetrics().density);
  }

  public static Point locationOnScreen(@NonNull final View view) {
    final int[] location = new int[2];

    //@doc the argument must be an array of two integers.
    view.getLocationOnScreen(location);
    return new Point(location[0], location[1]);
  }

  //More of an assurance
  public static void fixPopupLocation(@NonNull final PopupWindow popupWindow,
      @NonNull final Point desiredLocation) {
    popupWindow.getContentView().post(() -> {
      final Point actualLocation = locationOnScreen(popupWindow.getContentView());

      if (!(actualLocation.x == desiredLocation.x && actualLocation.y == desiredLocation.y)) {
        final int differenceX = actualLocation.x - desiredLocation.x;
        final int differenceY = actualLocation.y - desiredLocation.y;

        final int fixedOffsetX;
        final int fixedOffsetY;

        if (actualLocation.x > desiredLocation.x) {  //Agar zayada right sided hai
          fixedOffsetX = desiredLocation.x - differenceX;
        } else {
          fixedOffsetX = desiredLocation.x + differenceX;
        }

        if (actualLocation.y > desiredLocation.y) {  //Agar zayada upar ha
          fixedOffsetY = desiredLocation.y - differenceY;
        } else {
          fixedOffsetY = desiredLocation.y + differenceY;
        }

        popupWindow.update(fixedOffsetX, fixedOffsetY, DONT_UPDATE_FLAG, DONT_UPDATE_FLAG);
      }
    });
  }

  @NonNull public static Rect getWindowVisibleDisplayFrame(@NonNull final Activity context) {
    final Rect rect = new Rect();
    context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
    return rect;
  }

  public static int getScreenHeight(@NonNull final Activity context) {
    final Point size = new Point();

    context.getWindowManager().getDefaultDisplay().getSize(size);

    return size.y;
  }

  @SuppressLint("ObsoleteSdkInt") @TargetApi(JELLY_BEAN)
  public static void removeOnGlobalLayoutListener(final View v, final
  ViewTreeObserver.OnGlobalLayoutListener listener) {
    if (SDK_INT < JELLY_BEAN) {
      v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    } else {
      v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }
  }

  public static Activity asActivity(Context context) {
    Context resullt = context;

    while (resullt instanceof ContextWrapper) {
      if (resullt instanceof Activity) {
        return (Activity) resullt;
      }

      resullt = ((ContextWrapper) resullt).getBaseContext();
    }
    throw new IllegalArgumentException("Passed context is not an Acitivty");
  }
}
