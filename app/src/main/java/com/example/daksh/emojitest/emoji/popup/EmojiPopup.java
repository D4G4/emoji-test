package com.example.daksh.emojitest.emoji.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import com.example.daksh.emojitest.emoji.EmojiView;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiManager;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiBackspaceClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiLongClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiPopupDismissListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiPopupShownListener;
import com.example.daksh.emojitest.emoji.listeners.OnSoftKeyboardCloseListener;
import com.example.daksh.emojitest.emoji.listeners.OnSoftKeyboardOpenListener;
import com.example.daksh.emojitest.emoji.recent.RecentEmoji;
import com.example.daksh.emojitest.emoji.recent.RecentEmojiManager;
import com.example.daksh.emojitest.emoji.ui.EmojiEditText;
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

  //Just to setup height and toggle keyboard
  //Case: Keyboard is already is opened state
  //The method will be triggered when either the size of the keyboard change or user tries to close the keyboard.
  //Basically we'll first launch the Default keyboard and overlap it with our EmojiKeyboard
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
      @NonNull RecentEmoji recent,
      @NonNull VariantEmoji variant,
      EmojiEditTextInterface editTextInterface, @ColorInt final int backgroundColor,
      @ColorInt final int iconColor, @ColorInt final int dividerColor) {
    this.context = Utils.asActivity(rootView.getContext());
    this.rootView = rootView.getRootView();
    this.recentEmoji = recent != null ? recent : new RecentEmojiManager(context);
    this.variantEmoji = variant != null ? variant : new VariantEmojiManager(context);
    this.editTextInterface = editTextInterface;

    popupWindow = new PopupWindow(context);

    //Log.e("DAKSH", "RecentEMoji is null? " + (recentEmoji == null));

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

    final EmojiView emojiView =
        new EmojiView(context, clickListener, longClickListener, this.recentEmoji, this.variantEmoji,
            backgroundColor, iconColor, dividerColor);

    emojiView.setOnEmojiBackspaceClickListener((v) -> {
      editTextInterface.backspace();

      if (onEmojiBackspaceClickListener != null) {
        onEmojiBackspaceClickListener.onEmojiBackspaceClicked(v);
      }
    });

    popupWindow.setContentView(emojiView);
    popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
    popupWindow.setBackgroundDrawable(
        new BitmapDrawable(context.getResources(), (Bitmap) null)); //To avoid borders and overdraw.

    popupWindow.setOnDismissListener(() -> {
      if (onEmojiPopupDismissListener != null) {
        onEmojiPopupDismissListener.onEmojiPopupDismiss();
      }
    });
  }//end of constructor

  public void toggle() {
    if (!popupWindow.isShowing()) {
      //Remove any previous listeners to avoid duplicates
      Utils.removeOnGlobalLayoutListener(context.getWindow().getDecorView(),
          onGlobalLayoutListener);
      context.getWindow()
          .getDecorView()
          .getViewTreeObserver()
          .addOnGlobalLayoutListener(onGlobalLayoutListener);

      if (isKeyboardOpen) {
        // if the keyboard is visible, simply show the emoji popup
        showAtBottom();
      } else if (editTextInterface instanceof View) {
        final View view = (View) editTextInterface;

        //  Open the SoftKeyboard first and immediately show the emoji popup.
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        showAtBottomPending();

        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(
            Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
      } else {
        throw new IllegalArgumentException("The provided editInterface isn't a View instance");
      }
    } else {
      dismiss();
    }

    //  Manually dispatch the event. In some cases this does not work out of the box reliably
    context.getWindow().getDecorView().getViewTreeObserver().dispatchOnGlobalLayout();
  }

  public boolean isShowing() {
    return popupWindow.isShowing();
  }

  void showAtBottom() {
    final Point desiredLocation =
        new Point(0, Utils.getScreenHeight(context) - popupWindow.getHeight());

    popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, desiredLocation.x, desiredLocation.y);
    Utils.fixPopupLocation(popupWindow, desiredLocation);

    if (onEmojiPopupShownListener != null) {
      onEmojiPopupShownListener.onEmojiPopupShown();
    }
  }

  private void showAtBottomPending() {
    if (isKeyboardOpen) {
      showAtBottom();
    } else {
      /**
       * //now it will be taken care at {@code {@link #onGlobalLayoutListener}
       */
      isPendingOpen = true;
    }
  }

  public void dismiss() {
    popupWindow.dismiss();
    variantPopup.dismiss();
    recentEmoji.persist();
    variantEmoji.persist();
  }

  public static final class Builder {
    @NonNull private final View rootView;
    @ColorInt private int backgroundColor;
    @ColorInt private int iconColor;
    @ColorInt private int dividerColor;
    @Nullable private OnEmojiPopupShownListener onEmojiPopupShownListener;
    @Nullable private OnEmojiPopupDismissListener onEmojiPopupDismissListener;
    @Nullable private OnSoftKeyboardOpenListener onSoftKeyboardOpenListener;
    @Nullable private OnSoftKeyboardCloseListener onSoftKeyboardCloseListener;
    @Nullable private OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;
    @Nullable private OnEmojiClickListener onEmojiClickListener;
    @Nullable private RecentEmoji recentEmoji;
    @Nullable private VariantEmoji variantEmoji;

    private Builder(final View rootView) {
      this.rootView = Utils.checkNotNull(rootView, "The root view can't be empty");
    }

    /**
     * @param rootView The root View of your layout.xml which will be used for calculating the
     * height of the keyboard.
     * @return builder for building the {@link EmojiPopup}
     */
    @CheckResult public static Builder fromRootView(final View rootView) {
      return new Builder(rootView);
    }

    @CheckResult
    public Builder setOnSoftKeyboardCloseListener(
        @Nullable final OnSoftKeyboardCloseListener listener) {
      onSoftKeyboardCloseListener = listener;
      return this;
    }

    @CheckResult
    public Builder setOnSoftKeyboardOpenListener(
        @Nullable final OnSoftKeyboardOpenListener listener) {
      onSoftKeyboardOpenListener = listener;
      return this;
    }

    @CheckResult
    public Builder setOnEmojiClickListener(@Nullable final OnEmojiClickListener listener) {
      onEmojiClickListener = listener;
      return this;
    }

    @CheckResult public Builder setOnEmojiPopupShownListener(
        @Nullable final OnEmojiPopupShownListener listener) {
      onEmojiPopupShownListener = listener;
      return this;
    }

    @CheckResult public Builder setOnEmojiPopupDismissListener(
        @Nullable final OnEmojiPopupDismissListener listener) {
      onEmojiPopupDismissListener = listener;
      return this;
    }

    @CheckResult public Builder setOnEmojiBackspaceClickListener(
        @Nullable final OnEmojiBackspaceClickListener listener) {
      onEmojiBackspaceClickListener = listener;
      return this;
    }

    /**
     * Allows you to pass your own implementation of recent emojis. If not providied then the
     * default one {@link RecentEmojiManager} will be used
     */
    @CheckResult public Builder setRecentEmoji(@Nullable final RecentEmoji recentEmoji) {
      this.recentEmoji = recentEmoji;
      return this;
    }

    /**
     * Allows you to pass your own implementation of variant emojis. If not provided the default one
     * {@link VariantEmojiManager} will be used.
     */
    @CheckResult public Builder setVariantEmoji(@Nullable final VariantEmoji variant) {
      variantEmoji = variant;
      return this;
    }

    @CheckResult public Builder setBackgroundColor(@ColorInt final int color) {
      backgroundColor = color;
      return this;
    }

    @CheckResult public Builder setIconColor(@ColorInt final int color) {
      iconColor = color;
      return this;
    }

    @CheckResult public Builder setDividerColor(@ColorInt final int color) {
      dividerColor = color;
      return this;
    }

    /**
     * EditText is needed so whenever we tap the emoji, it will be inserted into the {@link
     * EmojiEditText}
     */
    @CheckResult public EmojiPopup build(@NonNull final EmojiEditTextInterface editTextInterface) {
      EmojiManager.getInstance().verifyInstall();
      Utils.checkNotNull(editTextInterface, "EditText can't be null");

      final EmojiPopup emojiPopup =
          new EmojiPopup(rootView, recentEmoji, variantEmoji, editTextInterface, backgroundColor,
              iconColor, dividerColor);
      emojiPopup.onSoftKeyboardCloseListener = onSoftKeyboardCloseListener;
      emojiPopup.onSoftKeyboardOpenListener = onSoftKeyboardOpenListener;
      emojiPopup.onEmojiBackspaceClickListener = onEmojiBackspaceClickListener;
      emojiPopup.onEmojiClickListener = onEmojiClickListener;
      emojiPopup.onEmojiPopupShownListener = onEmojiPopupShownListener;
      emojiPopup.onEmojiPopupDismissListener = onEmojiPopupDismissListener;
      return emojiPopup;
    }
  }
}

