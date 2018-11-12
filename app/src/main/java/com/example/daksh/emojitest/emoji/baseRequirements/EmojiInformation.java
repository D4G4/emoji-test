package com.example.daksh.emojitest.emoji.baseRequirements;

import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by daksh
 */
public class EmojiInformation {
  public final boolean hasOnlyEmojis;
  @NonNull public final List<EmojiRange> emojiRangeList;

  EmojiInformation(final boolean hasOnlyEmojis, @NonNull final List<EmojiRange> emojiRangeList) {
    this.hasOnlyEmojis = hasOnlyEmojis;
    this.emojiRangeList = emojiRangeList;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {   //If it's the same object with same hashCode
      return true;
    }

    if (null == obj || getClass() != obj.getClass()) {
      return false;
    }

    final EmojiInformation that = (EmojiInformation) obj;
    return this.hasOnlyEmojis == that.hasOnlyEmojis && this.emojiRangeList.equals(
        that.emojiRangeList);
  }

  @Override
  public int hashCode() {
    int result = hasOnlyEmojis ? 1 : 0;
    result = 31 * result + emojiRangeList.hashCode();
    return result;
  }
}
