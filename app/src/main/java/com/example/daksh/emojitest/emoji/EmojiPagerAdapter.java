package com.example.daksh.emojitest.emoji;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiManager;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiLongClickListener;
import com.example.daksh.emojitest.emoji.recent.RecentEmoji;
import com.example.daksh.emojitest.emoji.variants.VariantEmoji;

/**
 * Created by daksh
 */
public class EmojiPagerAdapter extends PagerAdapter {
  private static final int RECENT_POSITION = 0;

  private final OnEmojiClickListener listener;
  private final OnEmojiLongClickListener longListener;
  private final RecentEmoji recentEmoji;
  private final VariantEmoji variantManager;

  private RecentEmojiGridView recentEmojiGridView;

  EmojiPagerAdapter(final OnEmojiClickListener listener,
      final OnEmojiLongClickListener longListener, final RecentEmoji recentEmoji,
      final VariantEmoji variantManager) {
    this.listener = listener;
    this.longListener = longListener;
    this.recentEmoji = recentEmoji;
    this.variantManager = variantManager;
    this.recentEmojiGridView = null;
  }

  @Override public int getCount() {
    //Log.i("DAKSH", "getting count " + EmojiManager.getInstance().getCategories().length + 1);
    return EmojiManager.getInstance().getCategories().length + 1; //+1 for recent emojis
  }

  @NonNull @Override public Object instantiateItem(@NonNull ViewGroup pager, int position) {
    final View newView;

    if (position == RECENT_POSITION) {
      newView =
          new RecentEmojiGridView(pager.getContext()).init(listener, longListener, recentEmoji);
      recentEmojiGridView = (RecentEmojiGridView) newView;
    } else {
      newView = new EmojiGridView(pager.getContext()).init(listener, longListener,
          EmojiManager.getInstance().getCategories()[position - 1], variantManager);
    }
    pager.addView(newView);
    return newView;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup pager, int position, @NonNull Object view) {
    pager.removeView((View) view);

    if (position == RECENT_POSITION) {
      recentEmojiGridView = null;
    }
  }

  @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view.equals(object);
  }

  public int numberOfRecentEmojis() {
    return recentEmoji.getRecentEmojis().size();
  }

  public void invalidateRecentEmojis() {
    Log.i("TEST", "invalidating recent emojis");
    if (recentEmojiGridView != null) {
      recentEmojiGridView.invalidateEmojis();
    }
  }
}
