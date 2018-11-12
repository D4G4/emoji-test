package com.example.daksh.emojitest.emoji.baseRequirements;

import android.support.annotation.NonNull;
import com.example.daksh.emojitest.emoji.utils.EmojiCategory;

/**
 * Created by daksh
 */
public interface EmojiProvider {
  /**
   * @return The Array of {@link EmojiCategory}.
   */
  @NonNull EmojiCategory[] getCategories();
}
