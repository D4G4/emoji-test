package com.example.daksh.emojitest.emoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.example.daksh.emojitest.emoji.baseRequirements.Emoji;
import java.lang.ref.WeakReference;

/**
 * Created by daksh
 */

/**
 * Just puts imageDrawable of a specific emoji into the passed imageView
 */
final public class EmojiImageLoadingTask extends AsyncTask<Emoji, Void, Drawable> {

  private final WeakReference<ImageView> imageViewReference;
  private final WeakReference<Context> contextReference;

  public EmojiImageLoadingTask(final ImageView imageView) {
    this.imageViewReference = new WeakReference<>(imageView);
    this.contextReference = new WeakReference<>(imageView.getContext());
  }

  @Override protected Drawable doInBackground(Emoji... emojis) {
    final Context context = contextReference.get();
    if (context != null && !isCancelled()) {
      return emojis[0].getDrawable(context);
    }
    return null;
  }

  @Override protected void onPostExecute(Drawable drawable) {
    if (!isCancelled() && drawable != null) {
      final ImageView imageView = imageViewReference.get();

      if (imageView != null) {
        imageView.setImageDrawable(drawable);
      }
    }
  }
}
