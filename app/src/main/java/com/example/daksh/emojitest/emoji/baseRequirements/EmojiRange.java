package com.example.daksh.emojitest.emoji.baseRequirements;

import android.support.annotation.NonNull;

/**
 * Created by daksh
 *
 * Each emoji covers specific number of characters in a given String;
 * This class maintains that "range" for each emoji.
 */
public class EmojiRange {
  public final int start;
  public final int end;
  public final Emoji emoji;

  EmojiRange(final int start, final int end, @NonNull final Emoji emoji) {
    this.start = start;
    this.end = end;
    this.emoji = emoji;
  }

  @Override public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }

    if (null == obj || getClass() != obj.getClass()) {
      return false;
    }

    final EmojiRange that = (EmojiRange) obj;

    return this.start == that.start && this.end == that.end && this.emoji == that.emoji;
  }

  /**
   * @return Custom built HashCode
   */
  @Override public int hashCode() {
    int result = start;
    result = 31 * result + end;
    result = 31 * result + emoji.hashCode();
    return result;
  }
}
