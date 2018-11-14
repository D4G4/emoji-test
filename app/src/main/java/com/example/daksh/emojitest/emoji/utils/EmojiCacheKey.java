package com.example.daksh.emojitest.emoji.utils;

/**
 * Created by daksh
 */
public final class EmojiCacheKey {
  private final int x;
  private final int y;

  public EmojiCacheKey(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  @Override public boolean equals(Object obj) {
    return obj instanceof EmojiCacheKey
        && x == ((EmojiCacheKey) obj).x
        && y == ((EmojiCacheKey) obj).y;
  }

  /**
   * Just making a unicque number
   */
  @Override public int hashCode() {
    return (x << 16) ^ y;
  }
}
