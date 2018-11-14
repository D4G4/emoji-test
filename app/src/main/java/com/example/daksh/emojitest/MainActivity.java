package com.example.daksh.emojitest;

import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.daksh.emojitest.emoji.popup.EmojiPopup;
import com.example.daksh.emojitest.emoji.ui.EmojiEditText;
import com.example.daksh.emojitest.emoji.ui.EmojiTextView;

public class MainActivity extends AppCompatActivity {

  ChatAdapter chatAdapter;

  EmojiPopup emojiPopup;

  EmojiEditText editText;
  EmojiTextView textView;
  ViewGroup rootView;
  ImageView emojiButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    chatAdapter = new ChatAdapter();

    editText = findViewById(R.id.emojiEditText);
    rootView = findViewById(R.id.rootView);
    emojiButton = findViewById(R.id.emojiButton);

    final ImageView sendButton = findViewById(R.id.main_activity_send);

    emojiButton.setColorFilter(ContextCompat.getColor(this, R.color.emoji_icons),
        PorterDuff.Mode.SRC_IN);

    sendButton.setColorFilter(ContextCompat.getColor(this, R.color.emoji_icons),
        PorterDuff.Mode.SRC_IN);

    emojiButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View ignore) {
        emojiPopup.toggle();
      }
    });
    sendButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View ignore) {
        final String text = editText.getText().toString().trim();

        if (text.length() > 0) {
          chatAdapter.add(text);

          editText.setText("");
        }
      }
    });

    final RecyclerView recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setAdapter(chatAdapter);
    recyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    setupEmojiPopup();
  }

  @Override public void onBackPressed() {
    if (emojiPopup != null && emojiPopup.isShowing()) {
      emojiPopup.dismiss();
    } else {
      super.onBackPressed();
    }
  }

  @Override protected void onStop() {
    if (emojiPopup != null) {
      emojiPopup.dismiss();
    }

    super.onStop();
  }

  private void setupEmojiPopup() {
    emojiPopup = EmojiPopup.Builder.fromRootView(rootView)
        .setOnEmojiPopupShownListener(() -> emojiButton.setImageResource(R.drawable.ic_keyboard))
        .setOnEmojiPopupDismissListener(
            () -> emojiButton.setImageResource(R.drawable.emoji_ios_category_smileysandpeople))
        .build(editText);
  }
}
