package com.example.daksh.emojitest.emoji.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.CallSuper;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import com.example.daksh.emojitest.R;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiManager;

/**
 * Created by dennis
 */
public class EmojiEditText extends AppCompatEditText implements EmojiEditTextInterface {

  private float emojiSize;

  public EmojiEditText(Context context) {
    this(context, null);
  }

  public EmojiEditText(Context context, AttributeSet attrs) {
    super(context, attrs);

    if (!isInEditMode()) {
      EmojiManager.getInstance().verifyInstall();
    }

    final Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
    final float defaultEmojiSize = fontMetrics.descent - fontMetrics.ascent;

    if (attrs == null) {
      emojiSize = defaultEmojiSize;
    } else {
      final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EmojiEditText);

      try {
        emojiSize = a.getDimension(R.styleable.EmojiEditText_emojiSize, defaultEmojiSize);
      } finally {
        a.recycle();
      }

      setText(getText());
    }
  }

  @Override @CallSuper
  protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
    final Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
    final float defaultEmojiSize = fontMetrics.descent - fontMetrics.ascent;
    EmojiManager.getInstance()
        .replaceWithImages(getContext(), getText(), emojiSize, defaultEmojiSize);
  }

  //region EmojiEditTextInterface
  @Override public void backspace() {
    //TODO: ENDCALL???
    final KeyEvent event =
        new KeyEvent(0, 0, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0,
            KeyEvent.KEYCODE_ENDCALL);

    dispatchKeyEvent(event); //TODO: Really necessary?
  }

  @Override public void input(Emoji emoji) {
    if (emoji != null) {
      final int start = getSelectionStart();
      final int end = getSelectionEnd();

      //TODO: Log this stuff
      if (start < 0) {
        append(emoji.getUnicode());
      } else {
        getText().replace(Math.min(start, end), Math.max(start, end), emoji.getUnicode(), 0,
            emoji.getUnicode().length());
      }
    }
  }

  @Override public float getEmojiSize() {
    return emojiSize;
  }

  @Override public void setEmojiSize(int pixels) {
    setEmojiSize(pixels, true);
  }

  @Override public void setEmojiSize(int pixels, boolean shouldInvalidate) {
    emojiSize = pixels;

    if (shouldInvalidate) {
      setText(getText());
    }
  }

  @Override public void setEmojiSizeRes(int res) {
    setEmojiSizeRes(res, true);
  }

  @Override public void setEmojiSizeRes(int res, boolean shouldInvalidate) {
    setEmojiSize(getResources().getDimensionPixelSize(res), shouldInvalidate);
  }
  //endregion
}
