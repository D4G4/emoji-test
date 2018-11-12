package com.example.daksh.emojitest.emoji.recent;

import android.support.annotation.NonNull;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import java.util.Collection;

/**
 * Created by daksh
 */
public interface RecentEmoji {

  /**
   * This method is called more than once hence it is recommended to hold a collection of recent
   * emojis.
   */
  @NonNull Collection<Emoji> getRecentEmojis();

  void addEmoji(@NonNull Emoji emoji);

  void persist();
}
