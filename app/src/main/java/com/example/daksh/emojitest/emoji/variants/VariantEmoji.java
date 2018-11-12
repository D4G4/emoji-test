package com.example.daksh.emojitest.emoji.variants;

import android.support.annotation.NonNull;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;

/**
 * Created by daksh
 */
public interface VariantEmoji {
  /**
   * Returns the variant for the passed emoji. Could be loaded from Database, SharedPreferences or
   * just HardCoded.
   *
   * @param desiredEmoji The emoji to retrieve the variant for. If none is found, the passed emoji
   * should be returned.
   */
  @NonNull Emoji getVariant(Emoji desiredEmoji);

  /**
   * Should add emoji to the list of variants. After executing this method, the {@code {@link
   * #getVariant(Emoji)}} should return the emoji/variant that was just added.
   */
  void addVariant(@NonNull Emoji newVariant);

  /**
   * We should persist all emojis.
   */
  void persist();
}
