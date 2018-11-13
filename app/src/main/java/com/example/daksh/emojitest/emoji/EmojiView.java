package com.example.daksh.emojitest.emoji;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.example.daksh.emojitest.R;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiManager;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiBackspaceClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiClickListener;
import com.example.daksh.emojitest.emoji.listeners.OnEmojiLongClickListener;
import com.example.daksh.emojitest.emoji.recent.RecentEmoji;
import com.example.daksh.emojitest.emoji.utils.EmojiCategory;
import com.example.daksh.emojitest.emoji.variants.VariantEmoji;
import java.util.concurrent.TimeUnit;

/**
 * Created by daksh
 */
public class EmojiView extends LinearLayout implements ViewPager.OnPageChangeListener {

  private static final long INITIAL_INTERVAL = TimeUnit.SECONDS.toMillis(1) / 2;
  private static final int NORMAL_INTERVAL = 50;

  @ColorInt private final int themeIconColor;
  @ColorInt private final int themeAccentColor;

  final ImageButton[] emojiTabs;
  private final EmojiePagerAdapter emojiePagerAdapter;

  @Nullable OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

  private int emojiTabLastSelectedIndex = -1;

  public EmojiView(final Context context, final OnEmojiClickListener onEmojiClickListener, final
  OnEmojiLongClickListener onEmojiLongClickListener, @NonNull final RecentEmoji recentEmoji,
      @NonNull final VariantEmoji variantManager, @ColorInt final int backgroundColor,
      @ColorInt final int iconColor, @ColorInt final int dividerColor) {
    super(context);

    View.inflate(context, R.layout.emoji_view, this);

    setOrientation(VERTICAL);
    setBackgroundColor(backgroundColor != 0 ? backgroundColor
        : ContextCompat.getColor(context, R.color.emoji_background));

    themeIconColor =
        iconColor != 0 ? iconColor : ContextCompat.getColor(context, R.color.emoji_icons);

    final TypedValue typedValue = new TypedValue();
    context.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
    themeAccentColor = typedValue.data;

    final ViewPager emojisPager = findViewById(R.id.emojis_pager);
    emojisPager.addOnPageChangeListener(this);

    final View emojiDivider = findViewById(R.id.emoji_divider);
    emojiDivider.setBackgroundColor(
        dividerColor != 0 ? dividerColor : getResources().getColor(R.color.emoji_divider));

    final EmojiCategory[] categories = EmojiManager.getInstance().getCategories();
    final LinearLayout emojiCategoriesTab = findViewById(R.id.emoji_categories_tab);

    emojiTabs = new ImageButton[categories.length + 2]; //Recent + Backspace

    emojiTabs[0] = inflateButton(context, R.drawable.emoji_recent, emojiCategoriesTab);
  }

  private ImageButton inflateButton(final Context context, @DrawableRes final int icon,
      final ViewGroup parent) {
    final ImageButton button =
        (ImageButton) LayoutInflater.from(context)
            .inflate(R.layout.emoji_category_image_button, parent, false);

    button.setImageDrawable(AppCompatResources.getDrawable(context, icon));

    //Keeps the source pixels that cover the destination pixels, discards the remaining source and destination pixels.
    button.setColorFilter(themeIconColor, PorterDuff.Mode.SRC_IN);

    parent.addView(button);
    return button;
  }

  @Override public void onPageScrolled(int i, float v, int i1) {

  }

  @Override public void onPageSelected(int i) {

  }

  @Override public void onPageScrollStateChanged(int i) {

  }
}
