package com.example.daksh.emojitest.emoji.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiManager;

/**
 * Created by dennis
 */
public class EmojiButton extends AppCompatButton {
  public EmojiButton(Context context) {
    super(context);
  }

  public EmojiButton(Context context, AttributeSet attrs) {
    super(context, attrs);

    if(!isInEditMode()) {
      EmojiManager.getInstance().verifyInstall();
    }

  }
}
