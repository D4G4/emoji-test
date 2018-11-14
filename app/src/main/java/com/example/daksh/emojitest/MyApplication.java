package com.example.daksh.emojitest;

import android.app.Application;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiManager;
import com.example.daksh.emojitest.emoji_ios.IosEmojiProvider;

/**
 * Created by daksh
 */
public class MyApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();

    EmojiManager.install(new IosEmojiProvider());
  }
}
