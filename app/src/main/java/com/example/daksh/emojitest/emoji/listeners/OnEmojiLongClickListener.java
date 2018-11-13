package com.example.daksh.emojitest.emoji.listeners;

import android.support.annotation.NonNull;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.ui.EmojiImageView;

/**
 * Created by daksh
 */
public interface OnEmojiLongClickListener {
  void onEmojiLongClick(@NonNull EmojiImageView emojiImage, @NonNull Emoji emoji);
}
