package com.example.daksh.emojitest.emoji.baseRequirements;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by daksh
 */
public class EmojiUtils {
  // \s will remove anything that is a space character (including space, tab characters etc
  private static final Pattern SPACE_REMOVAL = Pattern.compile("[\\s]");

  /**
   * returns true when the String contains only emojis. Note: whitespaces will be filtered out.
   */
  public static boolean hasOnlyEmojis(@Nullable final String text) {
    if (!TextUtils.isEmpty(text)) {
      final String inputWithoutSpaces =
          SPACE_REMOVAL.matcher(text).replaceAll(Matcher.quoteReplacement(""));

      return EmojiManager.getInstance()
          .getEmojiRepetitivePattern()
          .matcher(inputWithoutSpaces)
          .matches();
    }
    return false;
  }

  @NonNull public static List<EmojiRange> foundEmojis(@Nullable final String text) {
    return EmojiManager.getInstance().findAllEmojis(text);
  }

  /**
   * Provides emoji information of the text like whether that text contains only emojies and the
   * list of {@link EmojiRange}
   *
   * @see EmojiRange
   */
  @NonNull public static EmojiInformation getEmojiInformation(@Nullable final String text) {
    final List<EmojiRange> emojiRangeList = EmojiManager.getInstance().findAllEmojis(text);
    final boolean hasOnlyEmojis = hasOnlyEmojis(text);
    return new EmojiInformation(hasOnlyEmojis, emojiRangeList);
  }

  private EmojiUtils() {
    throw new AssertionError("No instances.");
  }
}
