package com.example.daksh.emojitest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiInformation;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiUtils;
import com.example.daksh.emojitest.emoji.ui.EmojiTextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daksh
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

  private final List<String> texts = new ArrayList<>();

  @NonNull @Override public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
    return new ChatViewHolder(inflater.inflate(R.layout.adapter_chat, viewGroup, false));
  }

  @Override public void onBindViewHolder(@NonNull ChatViewHolder chatViewHolder, int position) {
    final String text = texts.get(position);

    final EmojiInformation emojiInformation = EmojiUtils.getEmojiInformation(text);
    final int res;

    if (emojiInformation.hasOnlyEmojis && emojiInformation.emojiRangeList.size() == 1) {
      res = R.dimen.emoji_size_single_emoji;
    } else if (emojiInformation.hasOnlyEmojis && emojiInformation.emojiRangeList.size() > 1) {
      res = R.dimen.emoji_size_only_emoji;
    } else {
      res = R.dimen.emoji_size_default;
    }

    chatViewHolder.textView.setEmojiSizeRes(res, false);
    chatViewHolder.textView.setText(text);
  }

  @Override public int getItemCount() {
    return texts.size();
  }

  public void add(final String text) {
    texts.add(text);
    notifyItemInserted(getItemCount() + 1);
  }

  static class ChatViewHolder extends RecyclerView.ViewHolder {
    final EmojiTextView textView;

    ChatViewHolder(final View view) {
      super(view);

      textView = view.findViewById(R.id.adapter_chat_text_view);
    }
  }
}
