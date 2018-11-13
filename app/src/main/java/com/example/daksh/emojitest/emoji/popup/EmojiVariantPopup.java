package com.example.daksh.emojitest.emoji.popup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.example.daksh.emojitest.R;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiClickListener;
import com.example.daksh.emojitest.emoji.ui.EmojiImageView;
import com.example.daksh.emojitest.emoji.utils.Utils;
import java.util.List;

/**
 * Created by daksh
 */
public class EmojiVariantPopup {
  private static final int MARGIN = 2;

  @NonNull private final View rootView;
  @NonNull private PopupWindow popupWindow;

  @Nullable final OnEmojiClickListener listener;
  @Nullable EmojiImageView rootImageView;

  public EmojiVariantPopup(@NonNull View rootView,
      @Nullable OnEmojiClickListener listener) {
    this.rootView = rootView;
    this.listener = listener;
  }

  /**
   * Use {@code {@link #initView(Context, Emoji, int)}} to create the PopupWindow.
   *
   * Now prepare PopupWindow from the View(content)
   *
   * trigger measureMethod on popupWindow
   *
   * get the location of TappedEmoji on the screen and get a desired location on the basis of your
   * tapped emoji
   *
   * changed the locaiton of PopupWindow to desiredLocation/Point
   */
  public void show(@NonNull final EmojiImageView clickedImage, @NonNull final Emoji emoji) {
    dismiss(); //If any window is opened

    rootImageView = clickedImage;

    //R.layout.emoji_skin_popup
    final View content = initView(clickedImage.getContext(), emoji, clickedImage.getWidth());

    popupWindow = new PopupWindow(content, WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT);

    popupWindow.setFocusable(true);
    popupWindow.setOutsideTouchable(true);
    popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);

    //TODO: What?  Means, empty background? Isn't it redundant??
    popupWindow.setBackgroundDrawable(
        new BitmapDrawable(clickedImage.getContext().getResources(), (Bitmap) null));

    content.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

    final Point location = Utils.locationOnScreen(clickedImage);

    final Point desiredLocation =
        new Point(location.x - content.getMeasuredWidth() / 2 + clickedImage.getWidth() / 2,
            location.y - content.getMeasuredHeight());

    popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, desiredLocation.x, desiredLocation.y);

    //So when popupWindow is open, rootImage's touch won't work and it will simply dismiss the popupWindow
    rootImageView.getParent().requestDisallowInterceptTouchEvent(true);

    /* The idea is to place it one the top and center of the image,
        but it's usually not possible,
        sometimes the positon of the emoji is at the left-most or right-most side of the screen
        in that case, we need to move the content(container) a little.
     */
    Utils.fixPopupLocation(popupWindow, desiredLocation);
  }

  public void dismiss() {
    rootImageView = null;
    if (popupWindow != null) {
      popupWindow.dismiss();
      popupWindow = null;
    }
  }

  /**
   * This method Scaffolds the popupView; a LinerLayout (id = container, orientation= horizontal).
   * Places EmojiImageView inside the container.
   *
   * Here we have already decided the margin of the container and thus we will be using those
   * coordinates to setup margin of the EmojiImageView inside layout.emoji_item.xml
   */
  private View initView(Context context, Emoji emoji, int width) {
    final View result = View.inflate(context, R.layout.emoji_skin_popup, null);
    final LinearLayout imageContainer = result.findViewById(R.id.container);

    final List<Emoji> variants = emoji.getBase().getVariants();
    variants.add(0, emoji.getBase());

    final LayoutInflater inflater = LayoutInflater.from(context);

    //TODO: Needs refactoring
    for (final Emoji variant : variants) {
      final ImageView emojiImage =
          (ImageView) inflater.inflate(R.layout.emoji_item, imageContainer, false);

      //@doc: Per-child layout information for layouts that support margins.
      final ViewGroup.MarginLayoutParams layoutParams =
          (ViewGroup.MarginLayoutParams) emojiImage.getLayoutParams();

      final int margin = Utils.dpToPx(context, MARGIN);

      //  Use the same size for Emoji as in the picker
      layoutParams.width = width;
      layoutParams.setMargins(margin, margin, margin, margin);

      emojiImage.setImageDrawable(variant.getDrawable(context));

      emojiImage.setOnClickListener((view) -> {
        if (listener != null && rootImageView != null) {
          listener.onEmojiClick(rootImageView, variant);
        }
      });

      imageContainer.addView(emojiImage);
    }
    return result;
  }
}
