package com.example.daksh.emojitest.emoji.utils;

/**
 * Created by daksh
 */
public final class CacheKey {
  private final int x;
  private final int y;

  public CacheKey(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  @Override public boolean equals(Object obj) {
    return obj instanceof CacheKey
        && x == ((CacheKey) obj).x
        && y == ((CacheKey) obj).y;
  }

  /**
   * I have no idea why is this done.
   */
  @Override public int hashCode() {
    return (x << 16) ^ y;
  }
}
