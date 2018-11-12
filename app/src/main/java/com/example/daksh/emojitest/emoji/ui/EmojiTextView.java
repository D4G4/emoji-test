package com.example.daksh.emojitest.emoji.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.DimenRes;
import android.support.annotation.Px;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import com.example.daksh.emojitest.R;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiManager;

/**
 * Created by daksh
 */
@SuppressWarnings("DanglingJavadoc") public class EmojiTextView extends AppCompatTextView {

  private float emojiSize;

  public EmojiTextView(Context context) {
    this(context, null);
  }

  public EmojiTextView(Context context, AttributeSet attrs) {
    super(context, attrs);

    //For instance, if this View is being drawn by a visual user interface builder
    if (!isInEditMode()) {
      EmojiManager.getInstance().verifyInstall();
    }
    final Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
    final float defaultEmojiSize = fontMetrics.descent - fontMetrics.ascent;

    if (attrs == null) {
      emojiSize = defaultEmojiSize;
    } else {
      final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EmojiTextView);

      try {
        emojiSize = a.getDimension(R.styleable.EmojiTextView_emojiSize, defaultEmojiSize);
      } finally {
        a.recycle();
      }
    }
    setText(getText());
  }

  //Replace the emoji input by your custom emoji.
  @Override public void setText(CharSequence rawText, BufferType type) {
    final CharSequence text = rawText == null ? "" : rawText;
    final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
    final Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
    final float defaultEmojiSize = fontMetrics.descent - fontMetrics.ascent;
    EmojiManager.getInstance()
        .replaceWithImages(getContext(), spannableStringBuilder, emojiSize, defaultEmojiSize);
    super.setText(spannableStringBuilder, type);
  }

  /**
   * sets the emoji size in pixels and automatically invalidates the text nd renders it with the new
   * size
   */
  public final void setEmojiSize(@Px final int pixels) {
    setEmojiSize(pixels, true);
  }

  /**
   * @param pixels sets the emoji size
   * @param shouldInvalidate invalidates the text and renders it with the new size
   */
  public final void setEmojiSize(@Px final int pixels, final boolean shouldInvalidate) {
    emojiSize = pixels;

    if (shouldInvalidate) {
      setText(getText());
    }
  }

  public final void setEmojiSizeRes(@DimenRes final int res) {
    setEmojiSizeRes(res, true);
  }

  public final void setEmojiSizeRes(@DimenRes final int res, final boolean shouldInvalidate) {
    setEmojiSize(getResources().getDimensionPixelSize(res), shouldInvalidate);
  }
}
