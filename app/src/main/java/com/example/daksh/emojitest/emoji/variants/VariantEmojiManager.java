package com.example.daksh.emojitest.emoji.variants;

/**
 * Created by daksh
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * NOTE: This class does not maintain all the variants of a specific Emoji. When loading all the
 * emojis, we use this class to display the "saved variant" of the emoji.
 */
public final class VariantEmojiManager implements VariantEmoji {

  private static final String PREFERENCE_NAME = "variant-emoji-manager";
  private static final String EMOJI_DELIMETER = "~";
  private static final String VARIANT_EMOJIS = "variant-emojis";
  static final int VARIANTS_GUESS_SIZE = 5;

  @NonNull private final Context context;
  @NonNull private List<Emoji> variantsList = new ArrayList<>(0);

  public VariantEmojiManager(@NonNull Context context) {
    this.context = context.getApplicationContext();
  }

  private void initFromSharedPreferences() {
    final String savedRecentVariants = getPreferences().getString(VARIANT_EMOJIS, "");

    if (savedRecentVariants.length() > 0) {
      final StringTokenizer stringTokenizer =
          new StringTokenizer(savedRecentVariants, EMOJI_DELIMETER);

      variantsList = new ArrayList<>(stringTokenizer.countTokens());
      while (stringTokenizer.hasMoreTokens()) {
        final String token = stringTokenizer.nextToken();
        final Emoji emoji = EmojiManager.getInstance().findEmoji(token);

        if (emoji != null && emoji.getLength() == token.length()) {
          this.variantsList.add(emoji);
        }
      }
    }
  }

  /**
   * @param desiredEmoji The emoji to retrieve the variant for. If none is found, the passed emoji
   */
  @NonNull @Override public Emoji getVariant(Emoji desiredEmoji) {
    if (variantsList.isEmpty()) {
      initFromSharedPreferences();
    }
    final Emoji baseEmoji = desiredEmoji.getBase();

    for (int i = 0; i < variantsList.size(); i++) {
      final Emoji emoji = variantsList.get(i);

      if (baseEmoji.equals(emoji.getBase())) {
        return emoji;
      }
    }
    return desiredEmoji;
  }

  /**
   * Updates the variant of a specific emoji.
   */
  @Override public void addVariant(@NonNull Emoji newVariant) {
    final Emoji newVariantBase = newVariant.getBase();

    for (int i = 0; i < variantsList.size(); i++) {
      Emoji variant = variantsList.get(i);
      if (variant.getBase().equals(newVariantBase)) {
        if (variant.equals(newVariant)) {
          return; //Same skin-tone was used
        } else {
          variantsList.remove(i);
          variantsList.add(newVariant);
          return;
        }
      }
    }
    //In case of variant not found in our sharedPreferenced, we simply add it.
    variantsList.add(newVariant);
  }

  /**
   * Save in Preferences
   */
  @Override public void persist() {
    if (variantsList.size() > 0) {
      final StringBuilder stringBuilder =
          new StringBuilder(variantsList.size() * VARIANTS_GUESS_SIZE);

      for (int i = 0; i < variantsList.size(); i++) {
        stringBuilder.append(variantsList.get(i).getUnicode()).append(EMOJI_DELIMETER);
      }

      //remove delimiter at last position
      stringBuilder.setLength(stringBuilder.length() - EMOJI_DELIMETER.length());

      getPreferences().edit().putString(VARIANT_EMOJIS, stringBuilder.toString()).apply();
    } else {
      getPreferences().edit().remove(VARIANT_EMOJIS).apply();
    }
  }

  private SharedPreferences getPreferences() {
    return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
  }
}
