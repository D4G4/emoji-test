package com.example.daksh.emojitest.emoji.ui;

import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;

/**
 * Created by daksh
 */

/** The implementer must be a subclass of {@link android.view.View} */
public interface EmojiEditTextInterface extends EmojiViewInterface {
  void backspace();

  void input(Emoji emoji);

  float getEmojiSize();
}
