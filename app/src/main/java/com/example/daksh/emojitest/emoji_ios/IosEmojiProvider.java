package com.example.daksh.emojitest.emoji_ios;

import android.support.annotation.NonNull;
import com.example.daksh.emojitest.emoji.baseRequirements.EmojiProvider;
import com.example.daksh.emojitest.emoji.utils.EmojiCategory;
import com.example.daksh.emojitest.emoji_ios.category.ActivitiesCategory;
import com.example.daksh.emojitest.emoji_ios.category.AnimalsAndNatureCategory;
import com.example.daksh.emojitest.emoji_ios.category.FlagsCategory;
import com.example.daksh.emojitest.emoji_ios.category.FoodAndDrinkCategory;
import com.example.daksh.emojitest.emoji_ios.category.ObjectsCategory;
import com.example.daksh.emojitest.emoji_ios.category.SmileysAndPeopleCategory;
import com.example.daksh.emojitest.emoji_ios.category.SymbolsCategory;
import com.example.daksh.emojitest.emoji_ios.category.TravelAndPlacesCategory;

/**
 * Created by daksh
 */
public class IosEmojiProvider implements EmojiProvider {
  @NonNull @Override public EmojiCategory[] getCategories() {
    return new EmojiCategory[] {
        new SmileysAndPeopleCategory(),
        new AnimalsAndNatureCategory(),
        new FoodAndDrinkCategory(),
        new ActivitiesCategory(),
        new TravelAndPlacesCategory(),
        new ObjectsCategory(),
        new SymbolsCategory(),
        new FlagsCategory()
    };
  }
}
