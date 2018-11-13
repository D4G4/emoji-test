package com.example.daksh.emojitest.emoji;

import android.annotation.SuppressLint;
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
import com.example.daksh.emojitest.emoji.listeners.RepeatListener;
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

  final ImageButton[] categoryImageButtons;
  private final EmojiPagerAdapter emojiPagerAdapter;

  @Nullable OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

  private int lastSelectedIndex = -1;

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

    categoryImageButtons = new ImageButton[categories.length + 2]; //Recent + Backspace

    categoryImageButtons[0] = inflateButton(context, R.drawable.emoji_recent, emojiCategoriesTab);
    for (int i = 0; i < categories.length; i++) {
      categoryImageButtons[i + 1] =
          inflateButton(context, categories[i].getIcon(), emojiCategoriesTab);
    }
    categoryImageButtons[categoryImageButtons.length - 1] =
        inflateButton(context, R.drawable.emoji_backspace, emojiCategoriesTab);

    handleOnClicks(emojisPager);

    emojiPagerAdapter =
        new EmojiPagerAdapter(onEmojiClickListener, onEmojiLongClickListener, recentEmoji,
            variantManager);

    final int startIndex = emojiPagerAdapter.numberOfRecentEmojis() > 0 ? 0 : 1;
    emojisPager.setCurrentItem(startIndex);
    onPageSelected(startIndex);
  }

  //Adds a image button the the Parent view
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

  @SuppressLint("ClickableViewAccessibility")
  private void handleOnClicks(final ViewPager emojisPager) {
    for (int i = 0; i < categoryImageButtons.length - 1; i++) {
      categoryImageButtons[i].setOnClickListener(new EmojiTabsClickListener(emojisPager, i));
    }

    categoryImageButtons[categoryImageButtons.length - 1].setOnTouchListener(
        new RepeatListener(INITIAL_INTERVAL, NORMAL_INTERVAL, v -> {
          if (onEmojiBackspaceClickListener != null) {
            onEmojiBackspaceClickListener.onEmojiBackspaceClicked(v);
          }
        }));
  }

  @Override public void onPageScrolled(int i, float v, int i1) {

  }

  @Override public void onPageSelected(int i) {
    if (lastSelectedIndex != i) {
      //If selecting recentEmojiPage
      if (i == 0) {
        emojiPagerAdapter.invalidateRecentEmojis();
      }

      //If not recentEmojiPage and trying to go to backspaceButton
      if (lastSelectedIndex >= 0
          && lastSelectedIndex < categoryImageButtons.length) {
        categoryImageButtons[lastSelectedIndex].setSelected(false);
        categoryImageButtons[lastSelectedIndex].setColorFilter(themeIconColor,
            PorterDuff.Mode.SRC_IN);
      }

      categoryImageButtons[i].setSelected(true);
      categoryImageButtons[i].setColorFilter(themeIconColor, PorterDuff.Mode.SRC_IN);

      lastSelectedIndex = i;
    }
  }

  @Override public void onPageScrollStateChanged(int i) {

  }

  public void setOnEmojiBackspaceClickListener(
      @Nullable final OnEmojiBackspaceClickListener onEmojiBackspaceClickListener) {
    this.onEmojiBackspaceClickListener = onEmojiBackspaceClickListener;
  }

  static class EmojiTabsClickListener implements OnClickListener {
    private final ViewPager emojisPager;
    private final int position;

    public EmojiTabsClickListener(ViewPager emojisPager, int position) {
      this.emojisPager = emojisPager;
      this.position = position;
    }

    @Override public void onClick(View v) {
      emojisPager.setCurrentItem(position);
    }
  }
}
