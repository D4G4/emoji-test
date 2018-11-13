package com.example.daksh.emojitest.emoji.popup;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiBackspaceClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiLongClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiPopupDismissListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiPopupShownListener;
import com.example.daksh.emojitest.emoji.listeners.OnSoftKeyboardCloseListener;
import com.example.daksh.emojitest.emoji.listeners.OnSoftKeyboardOpenListener;
import com.example.daksh.emojitest.emoji.recent.RecentEmoji;
import com.example.daksh.emojitest.emoji.recent.RecentEmojiManager;
import com.example.daksh.emojitest.emoji.ui.EmojiEditTextInterface;
import com.example.daksh.emojitest.emoji.ui.EmojiImageView;
import com.example.daksh.emojitest.emoji.utils.Utils;
import com.example.daksh.emojitest.emoji.variants.VariantEmoji;
import com.example.daksh.emojitest.emoji.variants.VariantEmojiManager;

/**
 * Created by daksh
 */
public class EmojiPopup {
  static final int MIN_KEYBOARD_HEIGHT = 100;

  final View rootView;
  final Activity context;

  @NonNull final RecentEmoji recentEmoji;
  @NonNull final VariantEmoji variantEmoji;
  @NonNull final EmojiVariantPopup variantPopup;

  final PopupWindow popupWindow;
  final EmojiEditTextInterface editTextInterface;

  boolean isPendingOpen;
  boolean isKeyboardOpen;

  @Nullable OnEmojiPopupShownListener onEmojiPopupShownListener;
  @Nullable OnEmojiPopupDismissListener onEmojiPopupDismissListener;

  @Nullable OnSoftKeyboardCloseListener onSoftKeyboardCloseListener;
  @Nullable OnSoftKeyboardOpenListener onSoftKeyboardOpenListener;

  @Nullable OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;
  @Nullable OnEmojiClickListener onEmojiClickListener;

  final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener =
      new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override public void onGlobalLayout() {
          final Rect rect = Utils.getWindowVisibleDisplayFrame(context);
          final int heightDifference = Utils.getScreenHeight(context) - rect.bottom;

          if (heightDifference > Utils.dpToPx(context, MIN_KEYBOARD_HEIGHT)) {
            popupWindow.setHeight(heightDifference);
            popupWindow.setWidth(rect.right);

            if (!isKeyboardOpen && onSoftKeyboardOpenListener != null) {
              onSoftKeyboardOpenListener.onKeyboardOpen(heightDifference);
            }

            isKeyboardOpen = true;

            if (isPendingOpen) {
              EmojiPopup.this.showAtBottom();
              isPendingOpen = false;
            }
          } else {
            if (isKeyboardOpen) {
              isKeyboardOpen = false;

              if (onSoftKeyboardCloseListener != null) {
                onSoftKeyboardCloseListener.onKeyboardClose();
              }

              EmojiPopup.this.dismiss();
              Utils.removeOnGlobalLayoutListener(context.getWindow().getDecorView(),
                  onGlobalLayoutListener);
            }
          }
        }
      };

  public EmojiPopup(View rootView,
      @NonNull RecentEmoji recentEmoji,
      @NonNull VariantEmoji variantEmoji,
      EmojiEditTextInterface editTextInterface, @ColorInt final int backgroundColor,
      @ColorInt final int iconColor, @ColorInt final int dividerColor) {
    this.context = Utils.asActivity(rootView.getContext());
    this.rootView = rootView.getRootView();
    this.recentEmoji = recentEmoji != null ? recentEmoji : new RecentEmojiManager(context);
    this.variantEmoji = variantEmoji != null ? variantEmoji : new VariantEmojiManager(context);
    this.editTextInterface = editTextInterface;

    popupWindow = new PopupWindow(context);

    final OnEmojiLongClickListener longClickListener = new OnEmojiLongClickListener() {
      @Override
      public void onEmojiLongClick(@NonNull EmojiImageView view, @NonNull Emoji emoji) {
        variantPopup.show(view, emoji);
      }
    };

    final OnEmojiClickListener clickListener = new OnEmojiClickListener() {
      @Override public void onEmojiClick(@NonNull EmojiImageView emojiImage, @NonNull Emoji emoji) {
        editTextInterface.input(emoji);

        recentEmoji.addEmoji(emoji);
        variantEmoji.addVariant(emoji);
        emojiImage.updateEmoji(emoji);

        if (null != onEmojiClickListener) {
          onEmojiClickListener.onEmojiClick(emojiImage, emoji);
        }

        variantPopup.dismiss();
      }
    };

    variantPopup = new EmojiVariantPopup(this.rootView, clickListener);

    //EmojiView

  }//end of constructor

  void showAtBottom() {
    final Point desiredLocation =
        new Point(0, Utils.getScreenHeight(context) - popupWindow.getHeight());

    popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, desiredLocation.x, desiredLocation.y);
    Utils.fixPopupLocation(popupWindow, desiredLocation);

    if (onEmojiPopupShownListener != null) {
      onEmojiPopupShownListener.onEmojiPopupShown();
    }
  }

  void dismiss() {
    popupWindow.dismiss();
    variantPopup.dismiss();
    recentEmoji.persist();
    variantEmoji.persist();
  }
}
