package com.example.daksh.emojitest.emoji.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.example.daksh.emojitest.R;
import com.example.daksh.emojitest.emoji.EmojiImageLoadingTask;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiLongClickListener;

/**
 * Created by daksh
 *
 * {@code {@link #onMeasure(int, int)}} use this method to find the width and constraint the
 * ImageView into sqaure
 *
 * {@code {@link #onSizeChanged(int, int, int, int)}} always update the {@link Point}s with updated
 * dimensions, ends up drawing a triangle/square
 *
 * {@code {@link #onDraw(Canvas)}} draw a path with {@code {@link #paintVariantIndicator}} and
 * {@code {@link #pathVariantIndicator}}
 *
 * {@code {@link #setEmoji(Emoji)}} uses {@code {@link #imageLoadingTask}} to load image from
 * corresponding emoji. Also configures {@link OnEmojiClickListener} & {@link
 * OnEmojiLongClickListener}
 */
public class EmojiImageView extends AppCompatImageView {

  private static final int VARIANT_INDICATOR_PART_AMOUNT = 6; //TODO: What are they? Play with them
  private static final int VARIANT_INDICATOR_PART = 5;

  Emoji currentEmoji;

  OnEmojiClickListener emojiClickListener;
  OnEmojiLongClickListener longClickListener;

  private final Paint paintVariantIndicator = new Paint();
  private final Path pathVariantIndicator = new Path();

  private final Point toptVariantIndicatorPoint = new Point();
  private final Point bottomRightVariantIndicatorPoint = new Point();
  private final Point bottomLeftVariantIndicatorPoint = new Point();

  private EmojiImageLoadingTask imageLoadingTask;

  private boolean hasVariants;

  public EmojiImageView(Context context, AttributeSet attrs) {
    super(context, attrs);

    paintVariantIndicator.setColor(ContextCompat.getColor(context, R.color.emoji_divider));
    paintVariantIndicator.setStyle(Paint.Style.FILL);
    paintVariantIndicator.setAntiAlias(true);
  }

  /**
   * Trying to constraint the View into a Square shape
   */
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    final int measuredWidth = getMeasuredWidth();

    //noinspection SuspiciousNameCombination
    setMeasuredDimension(measuredWidth, measuredWidth);
  }

  /**
   * Use new parameters to create a fresh Triangle, the actual drawing will be done inside {@code
   * {@link #onDraw(Canvas)}}
   */
  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);

    toptVariantIndicatorPoint.x = w;
    toptVariantIndicatorPoint.y = h / VARIANT_INDICATOR_PART_AMOUNT * VARIANT_INDICATOR_PART;

    bottomRightVariantIndicatorPoint.x = w;
    bottomRightVariantIndicatorPoint.y = h;

    bottomLeftVariantIndicatorPoint.x = w / VARIANT_INDICATOR_PART_AMOUNT * VARIANT_INDICATOR_PART;
    bottomLeftVariantIndicatorPoint.y = h;

    //Make a triangle
    pathVariantIndicator.rewind();
    pathVariantIndicator.moveTo(toptVariantIndicatorPoint.x, toptVariantIndicatorPoint.y);

    pathVariantIndicator.lineTo(bottomRightVariantIndicatorPoint.x,
        bottomRightVariantIndicatorPoint.y);

    pathVariantIndicator.lineTo(bottomLeftVariantIndicatorPoint.x,
        bottomLeftVariantIndicatorPoint.y);

    pathVariantIndicator.close();
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (hasVariants && getDrawable() != null) {
      canvas.drawPath(pathVariantIndicator, paintVariantIndicator);
    }
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();

    if (imageLoadingTask != null) {
      imageLoadingTask.cancel(true);
      imageLoadingTask = null;
    }
  }

  public void setEmoji(@NonNull final Emoji emoji) {
    if (!emoji.equals(currentEmoji)) {
      setImageDrawable(null);

      currentEmoji = emoji;
      hasVariants = emoji.getBase().hasVariants();

      if (imageLoadingTask != null) {
        imageLoadingTask.cancel(true);
      }

      setOnClickListener((view) -> {
        if (emojiClickListener != null) {
          emojiClickListener.onEmojiClick(EmojiImageView.this, currentEmoji);
        }
      });

      setOnLongClickListener(hasVariants ? (view) -> {
        longClickListener.onEmojiLongClick(EmojiImageView.this, currentEmoji);
        return true; //you have handled the event and it should stop here;
      } : null);

      imageLoadingTask = new EmojiImageLoadingTask(this);
      imageLoadingTask.execute(emoji);
    }
  }

  /**
   * Updates the emoji image directly. This should be called only for updating the variant being
   * displayed (of the same base emoji), since it does not run asynchronously and does not update
   * the internal listeners.
   *
   * @param variantOfSameEmoji The new emoji variant to show
   */
  public void updateEmoji(@NonNull final Emoji variantOfSameEmoji) {
    if (!variantOfSameEmoji.equals(currentEmoji)) {
      currentEmoji = variantOfSameEmoji;

      setImageDrawable(variantOfSameEmoji.getDrawable(this.getContext()));
    }
  }

  public void setOnEmojiClickListener(@Nullable final OnEmojiClickListener listener) {
    this.emojiClickListener = listener;
  }

  public void setOnEmojiLongClickListener(@Nullable final OnEmojiLongClickListener listener) {
    this.longClickListener = listener;
  }
}
