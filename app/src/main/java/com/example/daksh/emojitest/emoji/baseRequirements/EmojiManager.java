package com.example.daksh.emojitest.emoji.baseRequirements;

/**
 * Created by daksh
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.TextUtils;
import com.example.daksh.emojitest.emoji.utils.EmojiCategory;
import com.example.daksh.emojitest.emoji.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Where an {@link EmojiProvider} can be installed for further usage.
 *
 * The EmojiManager maintains the Regex The DefaultEmojiReplacer Categories of available emojis
 *
 * Is responsible for installing the emoji see {@link #install(EmojiProvider)}
 */
public class EmojiManager {
  private static final EmojiManager INSTANCE = new EmojiManager();
  private static final int GUESSED_UNICODE_AMOUNT = 3000;
  private static final int GUSSED_TOTAL_PATTERN_LENGTH = GUESSED_UNICODE_AMOUNT * 4;

  private static final Comparator<String> STRING_COMPARATOR =
      //compare(String, String)
      (firstString, secondString) -> {
        final int firstLength = firstString.length();
        final int secondLength = secondString.length();

        //Return 1 if secondString is longer than firstString
        return Integer.compare(secondLength, firstLength);
      };

  private final Map<String, Emoji> emojiMap = new LinkedHashMap<>(GUESSED_UNICODE_AMOUNT);
  private EmojiCategory[] categories;
  private Pattern emojiPattern;
  private Pattern emojiRepetitivePattern;
  private EmojiReplacer emojiReplacer;

  /**
   * As the name describes, this is a DefaultEmojiReplacer, it will replace all the Emojis in the
   * text with the Default emoji being set by the user.
   *
   * [1] Here, we find if the text consist of {@link MyEmojiSpan} typed emoji
   *
   * [2] Then we maintain a list of existing Emojis in a text along with their starting positions.
   *
   * [3]Now we find the list {@link EmojiRange} object from the text/String via {@code {@link
   * #findAllEmojis(CharSequence)}} followed be
   *
   * [4] iteration through the list and replacement of the emojis with default emoji.
   */
  private static final EmojiReplacer DEFAULT_EMOJI_REPLACER =
      //replaceWithImages() from EmojiReplacer
      ((Context context, Spannable text, float emojiSize, float defaultEmojiSize, EmojiReplacer fallback) -> {
        final EmojiManager emojiManager = EmojiManager.getInstance();

        //[1]
        // When an emoji is found and needs to be replaced in a CharSequence,
        // array of the EmojiSpan attached to the text
        final MyEmojiSpan[] existingSpans = text.getSpans(0, text.length(), MyEmojiSpan.class);

        //[2]
        final List<Integer> existingSpanStartingPositions = new ArrayList<>(existingSpans.length);
        for (MyEmojiSpan existingSpan : existingSpans) {
          existingSpanStartingPositions.add(text.getSpanStart(existingSpan));
        }
        //[3]
        final List<EmojiRange> foundEmojis = emojiManager.findAllEmojis(text);

        for (int i = 0; i < foundEmojis.size(); i++) {
          final EmojiRange emojiRange = foundEmojis.get(i);
          //[4]
          if (!existingSpanStartingPositions.contains(emojiRange.start)) {
            text.setSpan(new MyEmojiSpan(context, emojiRange.emoji, emojiSize), emojiRange.start,
                emojiRange.end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
        }
      });

  /*
   * Instantiates categories.
   * Decides whether the replacer should be Default or the one provided.
   * Creates the map {@code emojiMap};
   * Takes care of Variants of Emojis too;
   * Puts all the emojiCategories along with their corresponding variants into {@code emojiMap}.
   * Defines Regex patterns.
   */
  public static void install(@NonNull final EmojiProvider provider) {
    INSTANCE.categories = Utils.checkNotNull(provider.getCategories(), "categories == null");
    INSTANCE.emojiMap.clear();
    INSTANCE.emojiReplacer =
        provider instanceof EmojiRange ? (EmojiReplacer) provider : DEFAULT_EMOJI_REPLACER;

    final List<String> unicodesForPattern = new ArrayList<>(GUSSED_TOTAL_PATTERN_LENGTH);

    final int categoriesSize = INSTANCE.categories.length;

    for (int i = 0; i < categoriesSize; i++) {
      final Emoji[] emojis =
          Utils.checkNotNull(INSTANCE.categories[i].getEmojis(), "emojis == null");

      final int emojisSize = emojis.length;

      //Putting emojis in emojiMap
      for (final Emoji emoji : emojis) {
        final String unicode = emoji.getUnicode();
        final List<Emoji> variants = emoji.getVariants();

        INSTANCE.emojiMap.put(unicode, emoji);
        unicodesForPattern.add(unicode);

        //Putting corresponding variants into emojiMap
        for (Emoji variant : variants) {
          final String variantUnicode = variant.getUnicode();

          INSTANCE.emojiMap.put(variantUnicode, variant);
          unicodesForPattern.add(variantUnicode);
        }
      }
    }
    if (unicodesForPattern.isEmpty()) {
      throw new IllegalArgumentException(
          "Your EmojiProvider must have at least one category with at least one emoji.");
    }

    //Sort unicodes by length to the longest one gets matched first
    Collections.sort(unicodesForPattern, STRING_COMPARATOR);

    //TODO: I have no idea why is this pattern beiing produced
    final StringBuilder patternBuilder = new StringBuilder(GUSSED_TOTAL_PATTERN_LENGTH);
    for (int i = 0; i < unicodesForPattern.size(); i++) {
      patternBuilder.append(Pattern.quote(unicodesForPattern.get(i))).append("|");
    }

    final String regex = patternBuilder.deleteCharAt(patternBuilder.length() - 1).toString();
    INSTANCE.emojiPattern = Pattern.compile(regex);
    INSTANCE.emojiRepetitivePattern = Pattern.compile('(' + regex + ")+");
  }

  public static void destroy() {
    release();

    INSTANCE.emojiMap.clear();
    INSTANCE.categories = null;
    INSTANCE.emojiPattern = null;
    INSTANCE.emojiRepetitivePattern = null;
    INSTANCE.emojiReplacer = null;
  }

  /**
   * Releases all data associated with installed {@link Emoji}s. For the existing {@link
   * EmojiProvider}s this means the memory-heavy emoji sheet.
   *
   * In contrast to {@link #destroy()}, it does NOT destroy the internal data structures, and thus,
   * you do not need to {@link #install(EmojiProvider)} again before using the EmojiManager
   *
   * @see #destroy()
   */
  public static void release() {
    for (final Emoji emoji : INSTANCE.emojiMap.values()) {
      emoji.destroy();
    }
  }

  public void replaceWithImages(final Context context, final Spannable text, final float emojiSize,
      final float defaultEmojiSize) {
    verifyInstall();

    emojiReplacer.replaceWithImages(context, text, emojiSize, defaultEmojiSize,
        DEFAULT_EMOJI_REPLACER);
  }

  public EmojiCategory[] getCategories() {
    verifyInstall();
    return categories;
  }

  Pattern getEmojiRepetitivePattern() {
    return emojiRepetitivePattern;
  }

  /**
   * @return List of {@link EmojiRange} found in a text
   */
  @NonNull List<EmojiRange> findAllEmojis(@Nullable final CharSequence text) {
    verifyInstall();

    final List<EmojiRange> result = new ArrayList<>();

    if (!TextUtils.isEmpty(text)) {
      final Matcher matcher = emojiPattern.matcher(text);

      while (matcher.find()) {
        final Emoji found = findEmoji(text.subSequence(matcher.start(), matcher.end()));

        if (found != null) {
          result.add(new EmojiRange(matcher.start(), matcher.end(), found));
        }
      }
    }
    return result;
  }

  @Nullable public Emoji findEmoji(@Nullable final CharSequence candidate) {
    verifyInstall();

    //toString() will return unicode
    return emojiMap.get(candidate.toString());
  }

  public static EmojiManager getInstance() {
    return INSTANCE;
  }

  public void verifyInstall() {
    if (categories == null) {
      throw new IllegalStateException(
          "Please install an EmojiProvider through the EmojiManager.install() method first.");
    }
  }
}
