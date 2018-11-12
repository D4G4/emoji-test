package com.example.daksh.emojitest.emoji.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by daksh
 */
public class Utils {
  @NonNull public static <T> T checkNotNull(@Nullable final T reference, final String message) {
    if (reference == null) {
      throw new IllegalArgumentException(message);
    }
    return reference;
  }
}
