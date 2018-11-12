package com.example.daksh.emojitest.emoji.ui;

import android.support.annotation.DimenRes;
import android.support.annotation.Px;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;

/**
 * Created by daksh The implementer must be a subclass of {@link android.view.View}
 */
public interface EmojiEditTextInterface {
  void backspace();

  void input(Emoji emoji);

  float getEmojiSize();

  /**
   * sets the emoji size in pixels and automatically invalidates the text and renders it with the
   * new size
   */
  void setEmojiSize(@Px int pixels);

  /**
   * @param pixels sets the emoji size
   * @param shouldInvalidate invalidates the text and renders it with the new size
   */
  void setEmojiSize(@Px int pixels, boolean shouldInvalidate);

  void setEmojiSizeRes(@DimenRes int res);

  void setEmojiSizeRes(@DimenRes int res, boolean shouldInvalidate);
}
