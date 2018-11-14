package com.example.daksh.emojitest.emoji_ios;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.LruCache;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import com.example.daksh.emojitest.emoji.utils.EmojiCacheKey;
import java.lang.ref.SoftReference;

/**
 * Created by daksh
 */
public class IosEmoji extends Emoji {
  private static final Object LOCK = new Object();

  private static final int NUM_STRIPS = 51;
  private static final SoftReference[] SOFT_STRIP_BITMAP_REFERENCE = new SoftReference[NUM_STRIPS];

  private static final int CACHE_SIZE = 100;
  private static final LruCache<EmojiCacheKey, Bitmap> BITMAP_CACHE = new LruCache<>(CACHE_SIZE);

  private static final int SPRITE_SIZE = 64;
  private static final int SPRITE_SIZE_INC_BORDER = 66;

  static {
    for (int i = 0; i < NUM_STRIPS; i++) {
      SOFT_STRIP_BITMAP_REFERENCE[i] = new SoftReference<Bitmap>(null);
    }
  }

  private final int x;
  private final int y;

  public IosEmoji(final int codePoint, final int x, final int y) {
    super(codePoint, -1);

    this.x = x;
    this.y = y;
  }

  public IosEmoji(final int codePoint, final int x, final int y, final Emoji... variants) {
    super(codePoint, -1);

    this.x = x;
    this.y = y;
  }

  public IosEmoji(@NonNull final int[] codePoints, final int x, final int y) {
    super(codePoints, -1);
    this.x = x;
    this.y = y;
  }

  public IosEmoji(@NonNull final int[] codePoints, final int x, final int y,
      final Emoji... variants) {
    super(codePoints, -1, variants);
    this.x = x;
    this.y = y;
  }

  /**
   * Now that we have x and y coordinates of the emoji sinde the strip, simply create a bitmap out
   * of it and return it
   *
   * A {@link EmojiCacheKey} will be generated with the combination of x and y coordinates
   */
  @NonNull @Override public Drawable getDrawable(@NonNull final Context context) {
    final EmojiCacheKey key = new EmojiCacheKey(x, y);
    final Bitmap bitmap = BITMAP_CACHE.get(key);

    if (bitmap != null) {
      return new BitmapDrawable(context.getResources(), bitmap);
    }

    final Bitmap strip = loadStrip(context);
    final Bitmap cut =
        Bitmap.createBitmap(strip, 1, y * SPRITE_SIZE_INC_BORDER + 1, SPRITE_SIZE, SPRITE_SIZE);
    BITMAP_CACHE.put(key, cut);

    return new BitmapDrawable(context.getResources(), cut);
  }

  private Bitmap loadStrip(Context context) {
    Bitmap strip = (Bitmap) SOFT_STRIP_BITMAP_REFERENCE[x].get();
    if (strip == null) {
      synchronized (LOCK) {
        strip = (Bitmap) SOFT_STRIP_BITMAP_REFERENCE[x].get();
        if (strip == null) {
          final Resources resources = context.getResources();

          final int resId =
              resources.getIdentifier("emoji_ios_sheet_" + x, "drawable", context.getPackageName());

          strip = BitmapFactory.decodeResource(resources, resId);
          SOFT_STRIP_BITMAP_REFERENCE[x] = new SoftReference<>(strip);
        }
      }
    }
    return strip;
  }

  @Override public void destroy() {
    synchronized (LOCK) {
      BITMAP_CACHE.evictAll();
      for (int i = 0; i < NUM_STRIPS; i++) {
        final Bitmap strip = (Bitmap) SOFT_STRIP_BITMAP_REFERENCE[i].get();
        if (strip != null) {
          strip.recycle();
          SOFT_STRIP_BITMAP_REFERENCE[i].clear();
        }
      }
    }
  }
}
