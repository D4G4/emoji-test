package com.example.daksh.emojitest.emoji.utils;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;

/**
 * Interface for defining a category
 */
public interface EmojiCategory {
  /**
   * Returns all the emojis it can display
   */
  @NonNull Emoji[] getEmojis();

  /**
   * Returns the icon of the category that should be displayed;
   */
  @DrawableRes int getIcon();
}
