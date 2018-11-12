package com.example.daksh.emojitest.emoji.baseRequirements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

/**
 * Created by daksh
 *
 * This class helps us to draw our custom Drawable (our custom emoji) inside a TextSpan. We
 * calculate the font size and then paint a {@link Canvas} with the {@code {@link
 * #desiredFontSize}}
 *
 * Update Paint objet's FontMetrices in {@code {@link #getSize(Paint, CharSequence, int, int,
 * Paint.FontMetricsInt)}} which will further be used inside {@code {@link #draw(Canvas,
 * CharSequence, int, int, float, int, int, int, Paint)}}
 */
final class MyEmojiSpan extends DynamicDrawableSpan {

  private final float desiredFontSize;
  private final Context context;
  private final Emoji emoji;
  private Drawable defferedDrawable;

  MyEmojiSpan(final Context context, final Emoji emoji, final float desiredFontSize) {
    this.context = context;
    this.desiredFontSize = desiredFontSize;
    this.emoji = emoji;
  }

  @Override public Drawable getDrawable() {
    if (defferedDrawable == null) {
      defferedDrawable = emoji.getDrawable(context);
      defferedDrawable.setBounds(0, 0, (int) desiredFontSize, (int) desiredFontSize);
    }
    return defferedDrawable;
  }

  @Override
  public int getSize(Paint paint, CharSequence text, int start,
      int end, Paint.FontMetricsInt fontMetrics) {

    if (fontMetrics != null) {
      final Paint.FontMetrics paintFontMetrics = paint.getFontMetrics();

      //This will usually be -ve
      final float fontHeight = paintFontMetrics.descent - paintFontMetrics.ascent;

      //Max height from baseline
      final float centerY = paintFontMetrics.ascent + fontHeight / 2;

      fontMetrics.ascent = (int) (centerY - desiredFontSize / 2);
      fontMetrics.top = fontMetrics.ascent;

      fontMetrics.bottom = (int) (centerY + desiredFontSize / 2);
      fontMetrics.descent = fontMetrics.bottom;
    }
    return (int) desiredFontSize;
  }

  @Override
  public void draw(Canvas canvas, CharSequence text, int start,
      int end, float x, int top, int y, int bottom, Paint paint) {
    final Drawable drawable = getDrawable();

    final Paint.FontMetrics paintFontMetrics = paint.getFontMetrics();
    final float fontHeight = paintFontMetrics.descent - paint.ascent();
    final float centerY = y + paintFontMetrics.descent - fontHeight / 2;
    final float transitionY = centerY - desiredFontSize / 2;

    canvas.save();
    canvas.translate(x, transitionY);
    drawable.draw(canvas);
    canvas.restore();
  }
}
