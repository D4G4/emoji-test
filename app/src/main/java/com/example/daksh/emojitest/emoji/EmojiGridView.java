package com.example.daksh.emojitest.emoji;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.GridView;
import com.example.daksh.emojitest.R;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiLongClickListener;
import com.example.daksh.emojitest.emoji.utils.EmojiCategory;
import com.example.daksh.emojitest.emoji.variants.VariantEmoji;

/**
 * Created by daksh
 */
public class EmojiGridView extends GridView {
  protected EmojiArrayAdapter emojiArrayAdapter;

  public EmojiGridView(Context context) {
    super(context);

    final Resources resources = getResources();

    final int width = resources.getDimensionPixelSize(R.dimen.emoji_grid_view_column_width);
    final int spacing = resources.getDimensionPixelSize(R.dimen.emoji_grid_view_spacing);

    setColumnWidth(width);

    setHorizontalSpacing(spacing);
    setVerticalSpacing(spacing);
    setPadding(spacing, spacing, spacing, spacing);
    setNumColumns(AUTO_FIT);

    setClipToPadding(false);
    setVerticalScrollBarEnabled(false);
  }

  public EmojiGridView init(@Nullable final OnEmojiClickListener onEmojiClickListener,
      @Nullable final
      OnEmojiLongClickListener onEmojiLongClickListener, @NonNull final EmojiCategory category,
      @NonNull final
      VariantEmoji variantManager) {

    emojiArrayAdapter = new EmojiArrayAdapter(getContext(), category.getEmojis(), variantManager,
        onEmojiClickListener, onEmojiLongClickListener);

    setAdapter(emojiArrayAdapter);

    return this;
  }
}
