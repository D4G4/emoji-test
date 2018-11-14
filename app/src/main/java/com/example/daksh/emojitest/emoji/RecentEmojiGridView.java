package com.example.daksh.emojitest.emoji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiLongClickListener;
import com.example.daksh.emojitest.emoji.recent.RecentEmoji;
import java.util.Collection;

/**
 * Created by daksh
 */
public class RecentEmojiGridView extends EmojiGridView {
  private RecentEmoji recentEmojis;

  public RecentEmojiGridView(@NonNull final Context context) {
    super(context);
  }

  public RecentEmojiGridView init(@Nullable final OnEmojiClickListener onEmojiClickListener,
      @Nullable final OnEmojiLongClickListener onEmojiLongClickListener,
      @NonNull final RecentEmoji recentEmoji) {
    this.recentEmojis = recentEmoji;

    final Collection<Emoji> emojis = recentEmojis.getRecentEmojis();
    emojiArrayAdapter = new EmojiArrayAdapter(getContext(), emojis.toArray(new Emoji[0]), null,
        onEmojiClickListener, onEmojiLongClickListener);

    setAdapter(emojiArrayAdapter);
    return this;
  }

  public void invalidateEmojis() {
    Log.i("TEST", "updating");
    emojiArrayAdapter.updateEmojis(recentEmojis.getRecentEmojis());
  }
}
