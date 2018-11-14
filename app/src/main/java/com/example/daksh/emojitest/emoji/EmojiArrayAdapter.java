package com.example.daksh.emojitest.emoji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.daksh.emojitest.R;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiLongClickListener;
import com.example.daksh.emojitest.emoji.ui.EmojiImageView;
import com.example.daksh.emojitest.emoji.utils.Utils;
import com.example.daksh.emojitest.emoji.variants.VariantEmoji;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by daksh
 */
public class EmojiArrayAdapter extends ArrayAdapter<Emoji> {
  @Nullable private final VariantEmoji variantManager;
  @Nullable private final OnEmojiClickListener listener;
  @Nullable private final OnEmojiLongClickListener longClickListener;

  EmojiArrayAdapter(@NonNull final Context context, @NonNull final Emoji[] emojis,
      @Nullable final VariantEmoji variantManager,
      @Nullable final OnEmojiClickListener listener,
      @Nullable final OnEmojiLongClickListener longClickListener) {
    super(context, 0, new ArrayList<>(Arrays.asList(emojis)));

    this.variantManager = variantManager;
    this.listener = listener;
    this.longClickListener = longClickListener;
  }

  @NonNull @Override
  public View getView(int position, @Nullable View convertView,
      @NonNull ViewGroup parent) {
    Log.i("DAKSH", "------------------------------------------ getView");
    EmojiImageView image = (EmojiImageView) convertView;

    final Context context = getContext();

    if (image == null) {
      image =
          (EmojiImageView) LayoutInflater.from(context).inflate(R.layout.emoji_item, parent, false);
      image.setOnEmojiClickListener(listener);
      image.setOnEmojiLongClickListener(longClickListener);
    }

    final Emoji emoji = Utils.checkNotNull(getItem(position), "emoji == null");
    final Emoji variantToUse = variantManager == null ? emoji : variantManager.getVariant(emoji);
    image.setEmoji(variantToUse);

    return image;
  }

  public void updateEmojis(final Collection<Emoji> emojis) {
    clear();
    addAll(emojis);
    notifyDataSetChanged();
    Log.i("TEST", "Calling notify dataset changed");
  }

  @Override public int getCount() {
    return super.getCount();
  }
}
