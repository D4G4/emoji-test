package com.example.daksh.emojitest.emoji.baseRequirements;

/**
 * Created by daksh
 */

import android.content.Context;
import android.text.Spannable;

/**
 * {@link EmojiProvider} can implement this interface to perform text emoji image replacement in a
 * more efficient manner. For instance, the GoogleCompatEmojiProvider calls the corresponding
 * AppCompat EmojiSupport library to replace method directly for emoji the default size.
 */
public interface EmojiReplacer {
  void replaceWithImages(Context context, Spannable text, float emojiSize, float defaultEmojiSize,
      EmojiReplacer fallback);
}
