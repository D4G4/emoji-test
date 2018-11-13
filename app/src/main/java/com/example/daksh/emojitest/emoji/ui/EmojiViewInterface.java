package com.example.daksh.emojitest.emoji.ui;

import android.support.annotation.DimenRes;
import android.support.annotation.Px;

/**
 * Created by daksh
 */
public interface EmojiViewInterface {

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

  /**
   * @param res sets the emoji size in pixels with the provided resource
   * @param shouldInvalidate invalidates the text and renders it with the new size
   */
  void setEmojiSizeRes(@DimenRes int res, boolean shouldInvalidate);
}
