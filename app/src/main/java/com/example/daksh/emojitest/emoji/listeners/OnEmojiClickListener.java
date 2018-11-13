package com.example.daksh.emojitest.emoji.listeners;

import android.support.annotation.NonNull;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.ui.EmojiImageView;

/**
 * Created by daksh
 */
public interface OnEmojiClickListener {
  void onEmojiClick(@NonNull EmojiImageView emojiImage, @NonNull Emoji emoji);
}
