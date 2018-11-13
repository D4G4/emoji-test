package com.example.daksh.emojitest.emoji.recent;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by daksh
 */
public class RecentEmojiManager implements RecentEmoji {

  //Emoji with it's corresponding TimeStamp
  static class EmojiData {
    final Emoji emoji;
    final Long timeStamp;

    public EmojiData(Emoji emoji, Long timeStamp) {
      this.emoji = emoji;
      this.timeStamp = timeStamp;
    }
  }

  //filters emoji with same base and skinTone and returns sorted emoji based on COMPARATOR
  static class EmojiDataList {
    static final Comparator<EmojiData> EMOJI_DATA_TIMESTAMP_COMPARATOR =
        (EmojiData lhs, EmojiData rhs) -> rhs.timeStamp.compareTo(lhs.timeStamp);

    @NonNull final List<EmojiData> recentEmojisList;

    public EmojiDataList(final int size) {
      recentEmojisList = new ArrayList<>(size);
    }

    void add(final Emoji emoji) {
      Log.i("RecentEmojiManager", "emojiAdded  timeStamp -> currentTimeInMillis");
      add(emoji, System.currentTimeMillis());
    }

    /**
     * adds an emoji to the top of the {@code {@link #recentEmojisList}}
     */
    void add(final Emoji emoji, final long timeStamp) {
      Log.i("RecentEmojiManager", "emojiAdded  timeStamp " + timeStamp);
      final Iterator<EmojiData> dataIterator = recentEmojisList.iterator();

      final Emoji emojiBase = emoji.getBase();

      while (dataIterator.hasNext()) {
        final EmojiData emojiData = dataIterator.next();
        //Do the comparision by base so skin tones are only saved once
        if (emojiData.emoji.getBase().equals(emojiBase)) {
          dataIterator.remove();
        }
      }//end of loop
      recentEmojisList.add(0, new EmojiData(emoji, timeStamp));

      if (recentEmojisList.size() > MAX_RECENTS) {
        recentEmojisList.remove(MAX_RECENTS);
      }
    }

    Collection<Emoji> getRecentEmojis() {
      Collections.sort(recentEmojisList, EMOJI_DATA_TIMESTAMP_COMPARATOR);

      final Collection<Emoji> sortedEmojis = new ArrayList<>(recentEmojisList.size());

      for (final EmojiData data : recentEmojisList) {
        sortedEmojis.add(data.emoji);
      }

      return sortedEmojis;
    }

    int size() {
      return recentEmojisList.size();
    }

    EmojiData get(final int index) {
      return recentEmojisList.get(index);
    }
  }

  private static final String PREFERENCE_NAME = "emoji-recent-manager";
  private static final String TIME_DELIMETER = ";";
  private static final String EMOJI_DELIMETER = "~";
  private static final String RECENT_EMOJIS = "recent-emojis";

  static final int EMOJI_GUESS_SIZE = 5;
  static final int MAX_RECENTS = 40;

  @NonNull private final Context context;
  @NonNull private EmojiDataList emojiDataList = new EmojiDataList(0);

  public RecentEmojiManager(@NonNull Context context) {
    this.context = context.getApplicationContext();
  }

  //From sharedPrefs
  @NonNull @Override public Collection<Emoji> getRecentEmojis() {
    if (emojiDataList.size() == 0) {
      final String savedRecentEmojis = getPreferences().getString(RECENT_EMOJIS, "");

      if (savedRecentEmojis.length() > 0) {
        final StringTokenizer stringTokenizer =
            new StringTokenizer(savedRecentEmojis, EMOJI_DELIMETER);
        while (stringTokenizer.hasMoreTokens()) {
          final String token = stringTokenizer.nextToken();
          final String[] parts = token.split(TIME_DELIMETER);

          if (parts.length == 2) {
            final Emoji emoji = EmojiManager.getInstance().findEmoji(parts[0]);

            if (emoji != null && emoji.getLength() == parts[0].length()) {
              final long timeStamp = Long.parseLong(parts[1]);
              emojiDataList.add(emoji, timeStamp);
            }
          }
        }
      } else {
        emojiDataList = new EmojiDataList(0);
      }
    }
    return emojiDataList.getRecentEmojis();
  }

  @Override public void addEmoji(@NonNull Emoji emoji) {
    emojiDataList.add(emoji);
  }

  /**
   * Save to sharedPreferences
   */
  @Override public void persist() {
    if (emojiDataList.size() > 0) {
      final StringBuilder stringBuilder = new StringBuilder(emojiDataList.size() * EMOJI_GUESS_SIZE);

      for (int i = 0; i < emojiDataList.size(); i++) {
        final EmojiData data = emojiDataList.get(i);
        stringBuilder.append(data.emoji.getUnicode())
            .append(TIME_DELIMETER)
            .append(data.timeStamp)
            .append(EMOJI_DELIMETER);
      }
      stringBuilder.setLength(stringBuilder.length() - EMOJI_DELIMETER.length());

      getPreferences().edit().putString(RECENT_EMOJIS, stringBuilder.toString()).apply();
    }
  }

  private SharedPreferences getPreferences() {
    return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
  }
}
