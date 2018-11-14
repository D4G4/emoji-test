package com.example.daksh.emojitest.emoji.baseRequirements;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by daksh
 */
public class Emoji implements Serializable {
  private static final long serialVersionUID = 3L;

  @NonNull private final String unicode;
  @DrawableRes private final int resource;
  @NonNull private final List<Emoji> variants;
  @Nullable private Emoji base; // Used for Variants

  public Emoji(@NonNull final int[] unicodePoints, @DrawableRes final int resource) {
    this(unicodePoints, resource, new Emoji[0]);
  }

  public Emoji(final int codePoint, @DrawableRes final int resource) {
    this(codePoint, resource, new Emoji[0]);
  }

  public Emoji(final int unicodePoint, @DrawableRes final int resource, final Emoji... variants) {
    this(new int[] {unicodePoint}, resource, variants);
  }

  public Emoji(@NonNull final int[] unicodePoints, @DrawableRes final int resource,
      final Emoji... variants) {
    this.unicode = new String(unicodePoints, 0, unicodePoints.length);
    this.resource = resource;

    //  Arrays.asList() seems to always allocate a new object, even for empty list.
    this.variants = variants.length == 0 ? Collections.emptyList() : Arrays.asList(variants);
    for (final Emoji variant : variants) {
      variant.base = this;
    }
  }

  /**
   * @return unicode of a current {@code Emoji}
   */
  @NonNull public String getUnicode() {
    return unicode;
  }

  /**
   * @return drawable from list of Drawables for specific emoji type.
   */
  @NonNull public Drawable getDrawable(@NonNull final Context context) {
    return AppCompatResources.getDrawable(context, resource);
  }

  /**
   * @return List of {@code Emoji} variants/types of Emojis related to it. Like color of humans,
   * (black, brown, creeam, white...)
   */
  @NonNull public List<Emoji> getVariants() {
    return new ArrayList<>(variants);
  }

  /**
   * Returns the baseEmoji of a variant of emoji (yellow, white, brown...)
   */
  @NonNull public Emoji getBase() {
    Emoji result = this;

    while (result.base != null) {
      result = result.base;
    }
    return result;
  }

  public int getLength() {
    return unicode.length();
  }

  public boolean hasVariants() {
    return !variants.isEmpty();
  }

  public void destroy() {
    //  For inheritors to override
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (null == obj || getClass() != obj.getClass()) {
      return false;
    }

    final Emoji comparableEmoji = (Emoji) obj;

    return this.resource == comparableEmoji.resource
        && this.unicode.equals(comparableEmoji.unicode)
        && this.variants.equals(comparableEmoji.variants);
  }

  @Override public int hashCode() {
    int result = unicode.hashCode();
    result = 31 * result + resource;
    result = 31 * result + variants.hashCode();
    return result;
  }
}
