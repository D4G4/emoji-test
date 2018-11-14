package com.example.daksh.emojitest.emoji_ios.category;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import com.example.daksh.emojitest.R;
import com.example.daksh.emojitest.emoji.utils.EmojiCategory;
import com.example.daksh.emojitest.emoji_ios.IosEmoji;

/**
 * Created by daksh
 */
public class FlagsCategory implements EmojiCategory {
  private static final IosEmoji[] DATA = new IosEmoji[] {
      new IosEmoji(0x1F3C1, 9, 27),
      new IosEmoji(0x1F6A9, 35, 14),
      new IosEmoji(0x1F38C, 8, 31),
      new IosEmoji(0x1F3F4, 12, 19),
      new IosEmoji(new int[] {0x1F3F3, 0xFE0F}, 12, 15),
      new IosEmoji(new int[] {0x1F3F3, 0xFE0F, 0x200D, 0x1F308}, 12, 14),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1E8}, 0, 31),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1E9}, 0, 32),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1EA}, 0, 33),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1EB}, 0, 34),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1EC}, 0, 35),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1EE}, 0, 36),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1F1}, 0, 37),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1F2}, 0, 38),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1F4}, 0, 39),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1F6}, 0, 40),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1F7}, 0, 41),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1F8}, 0, 42),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1F9}, 0, 43),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1FA}, 0, 44),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1FC}, 0, 45),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1FD}, 0, 46),
      new IosEmoji(new int[] {0x1F1E6, 0x1F1FF}, 0, 47),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1E6}, 0, 48),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1E7}, 0, 49),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1E9}, 0, 50),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1EA}, 0, 51),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1EB}, 1, 0),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1EC}, 1, 1),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1ED}, 1, 2),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1EE}, 1, 3),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1EF}, 1, 4),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1F1}, 1, 5),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1F2}, 1, 6),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1F3}, 1, 7),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1F4}, 1, 8),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1F6}, 1, 9),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1F7}, 1, 10),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1F8}, 1, 11),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1F9}, 1, 12),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1FB}, 1, 13),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1FC}, 1, 14),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1FE}, 1, 15),
      new IosEmoji(new int[] {0x1F1E7, 0x1F1FF}, 1, 16),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1E6}, 1, 17),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1E8}, 1, 18),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1E9}, 1, 19),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1EB}, 1, 20),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1EC}, 1, 21),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1ED}, 1, 22),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1EE}, 1, 23),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1F0}, 1, 24),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1F1}, 1, 25),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1F2}, 1, 26),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1F3}, 1, 27),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1F4}, 1, 28),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1F5}, 1, 29),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1F7}, 1, 30),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1FA}, 1, 31),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1FB}, 1, 32),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1FC}, 1, 33),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1FD}, 1, 34),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1FE}, 1, 35),
      new IosEmoji(new int[] {0x1F1E8, 0x1F1FF}, 1, 36),
      new IosEmoji(new int[] {0x1F1E9, 0x1F1EA}, 1, 37),
      new IosEmoji(new int[] {0x1F1E9, 0x1F1EC}, 1, 38),
      new IosEmoji(new int[] {0x1F1E9, 0x1F1EF}, 1, 39),
      new IosEmoji(new int[] {0x1F1E9, 0x1F1F0}, 1, 40),
      new IosEmoji(new int[] {0x1F1E9, 0x1F1F2}, 1, 41),
      new IosEmoji(new int[] {0x1F1E9, 0x1F1F4}, 1, 42),
      new IosEmoji(new int[] {0x1F1E9, 0x1F1FF}, 1, 43),
      new IosEmoji(new int[] {0x1F1EA, 0x1F1E6}, 1, 44),
      new IosEmoji(new int[] {0x1F1EA, 0x1F1E8}, 1, 45),
      new IosEmoji(new int[] {0x1F1EA, 0x1F1EA}, 1, 46),
      new IosEmoji(new int[] {0x1F1EA, 0x1F1EC}, 1, 47),
      new IosEmoji(new int[] {0x1F1EA, 0x1F1ED}, 1, 48),
      new IosEmoji(new int[] {0x1F1EA, 0x1F1F7}, 1, 49),
      new IosEmoji(new int[] {0x1F1EA, 0x1F1F8}, 1, 50),
      new IosEmoji(new int[] {0x1F1EA, 0x1F1F9}, 1, 51),
      new IosEmoji(new int[] {0x1F1EA, 0x1F1FA}, 2, 0),
      new IosEmoji(new int[] {0x1F1EB, 0x1F1EE}, 2, 1),
      new IosEmoji(new int[] {0x1F1EB, 0x1F1EF}, 2, 2),
      new IosEmoji(new int[] {0x1F1EB, 0x1F1F0}, 2, 3),
      new IosEmoji(new int[] {0x1F1EB, 0x1F1F2}, 2, 4),
      new IosEmoji(new int[] {0x1F1EB, 0x1F1F4}, 2, 5),
      new IosEmoji(new int[] {0x1F1EB, 0x1F1F7}, 2, 6),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1E6}, 2, 7),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1E7}, 2, 8),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1E9}, 2, 9),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1EA}, 2, 10),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1EB}, 2, 11),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1EC}, 2, 12),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1ED}, 2, 13),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1EE}, 2, 14),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1F1}, 2, 15),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1F2}, 2, 16),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1F3}, 2, 17),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1F5}, 2, 18),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1F6}, 2, 19),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1F7}, 2, 20),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1F8}, 2, 21),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1F9}, 2, 22),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1FA}, 2, 23),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1FC}, 2, 24),
      new IosEmoji(new int[] {0x1F1EC, 0x1F1FE}, 2, 25),
      new IosEmoji(new int[] {0x1F1ED, 0x1F1F0}, 2, 26),
      new IosEmoji(new int[] {0x1F1ED, 0x1F1F2}, 2, 27),
      new IosEmoji(new int[] {0x1F1ED, 0x1F1F3}, 2, 28),
      new IosEmoji(new int[] {0x1F1ED, 0x1F1F7}, 2, 29),
      new IosEmoji(new int[] {0x1F1ED, 0x1F1F9}, 2, 30),
      new IosEmoji(new int[] {0x1F1ED, 0x1F1FA}, 2, 31),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1E8}, 2, 32),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1E9}, 2, 33),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1EA}, 2, 34),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1F1}, 2, 35),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1F2}, 2, 36),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1F3}, 2, 37),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1F4}, 2, 38),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1F6}, 2, 39),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1F7}, 2, 40),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1F8}, 2, 41),
      new IosEmoji(new int[] {0x1F1EE, 0x1F1F9}, 2, 42),
      new IosEmoji(new int[] {0x1F1EF, 0x1F1EA}, 2, 43),
      new IosEmoji(new int[] {0x1F1EF, 0x1F1F2}, 2, 44),
      new IosEmoji(new int[] {0x1F1EF, 0x1F1F4}, 2, 45),
      new IosEmoji(new int[] {0x1F1EF, 0x1F1F5}, 2, 46),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1EA}, 2, 47),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1EC}, 2, 48),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1ED}, 2, 49),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1EE}, 2, 50),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1F2}, 2, 51),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1F3}, 3, 0),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1F5}, 3, 1),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1F7}, 3, 2),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1FC}, 3, 3),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1FE}, 3, 4),
      new IosEmoji(new int[] {0x1F1F0, 0x1F1FF}, 3, 5),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1E6}, 3, 6),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1E7}, 3, 7),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1E8}, 3, 8),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1EE}, 3, 9),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1F0}, 3, 10),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1F7}, 3, 11),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1F8}, 3, 12),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1F9}, 3, 13),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1FA}, 3, 14),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1FB}, 3, 15),
      new IosEmoji(new int[] {0x1F1F1, 0x1F1FE}, 3, 16),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1E6}, 3, 17),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1E8}, 3, 18),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1E9}, 3, 19),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1EA}, 3, 20),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1EB}, 3, 21),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1EC}, 3, 22),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1ED}, 3, 23),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F0}, 3, 24),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F1}, 3, 25),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F2}, 3, 26),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F3}, 3, 27),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F4}, 3, 28),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F5}, 3, 29),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F6}, 3, 30),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F7}, 3, 31),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F8}, 3, 32),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1F9}, 3, 33),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1FA}, 3, 34),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1FB}, 3, 35),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1FC}, 3, 36),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1FD}, 3, 37),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1FE}, 3, 38),
      new IosEmoji(new int[] {0x1F1F2, 0x1F1FF}, 3, 39),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1E6}, 3, 40),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1E8}, 3, 41),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1EA}, 3, 42),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1EB}, 3, 43),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1EC}, 3, 44),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1EE}, 3, 45),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1F1}, 3, 46),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1F4}, 3, 47),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1F5}, 3, 48),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1F7}, 3, 49),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1FA}, 3, 50),
      new IosEmoji(new int[] {0x1F1F3, 0x1F1FF}, 3, 51),
      new IosEmoji(new int[] {0x1F1F4, 0x1F1F2}, 4, 0),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1E6}, 4, 1),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1EA}, 4, 2),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1EB}, 4, 3),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1EC}, 4, 4),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1ED}, 4, 5),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1F0}, 4, 6),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1F1}, 4, 7),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1F2}, 4, 8),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1F3}, 4, 9),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1F7}, 4, 10),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1F8}, 4, 11),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1F9}, 4, 12),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1FC}, 4, 13),
      new IosEmoji(new int[] {0x1F1F5, 0x1F1FE}, 4, 14),
      new IosEmoji(new int[] {0x1F1F6, 0x1F1E6}, 4, 15),
      new IosEmoji(new int[] {0x1F1F7, 0x1F1EA}, 4, 16),
      new IosEmoji(new int[] {0x1F1F7, 0x1F1F4}, 4, 17),
      new IosEmoji(new int[] {0x1F1F7, 0x1F1F8}, 4, 18),
      new IosEmoji(new int[] {0x1F1F7, 0x1F1FA}, 4, 19),
      new IosEmoji(new int[] {0x1F1F7, 0x1F1FC}, 4, 20),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1E6}, 4, 21),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1E7}, 4, 22),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1E8}, 4, 23),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1E9}, 4, 24),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1EA}, 4, 25),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1EC}, 4, 26),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1ED}, 4, 27),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1EE}, 4, 28),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1EF}, 4, 29),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1F0}, 4, 30),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1F1}, 4, 31),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1F2}, 4, 32),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1F3}, 4, 33),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1F4}, 4, 34),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1F7}, 4, 35),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1F8}, 4, 36),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1F9}, 4, 37),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1FB}, 4, 38),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1FD}, 4, 39),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1FE}, 4, 40),
      new IosEmoji(new int[] {0x1F1F8, 0x1F1FF}, 4, 41),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1E6}, 4, 42),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1E8}, 4, 43),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1E9}, 4, 44),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1EB}, 4, 45),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1EC}, 4, 46),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1ED}, 4, 47),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1EF}, 4, 48),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1F0}, 4, 49),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1F1}, 4, 50),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1F2}, 4, 51),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1F3}, 5, 0),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1F4}, 5, 1),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1F7}, 5, 2),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1F9}, 5, 3),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1FB}, 5, 4),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1FC}, 5, 5),
      new IosEmoji(new int[] {0x1F1F9, 0x1F1FF}, 5, 6),
      new IosEmoji(new int[] {0x1F1FA, 0x1F1E6}, 5, 7),
      new IosEmoji(new int[] {0x1F1FA, 0x1F1EC}, 5, 8),
      new IosEmoji(new int[] {0x1F1FA, 0x1F1F2}, 5, 9),
      new IosEmoji(new int[] {0x1F1FA, 0x1F1F8}, 5, 11),
      new IosEmoji(new int[] {0x1F1FA, 0x1F1FE}, 5, 12),
      new IosEmoji(new int[] {0x1F1FA, 0x1F1FF}, 5, 13),
      new IosEmoji(new int[] {0x1F1FB, 0x1F1E6}, 5, 14),
      new IosEmoji(new int[] {0x1F1FB, 0x1F1E8}, 5, 15),
      new IosEmoji(new int[] {0x1F1FB, 0x1F1EA}, 5, 16),
      new IosEmoji(new int[] {0x1F1FB, 0x1F1EC}, 5, 17),
      new IosEmoji(new int[] {0x1F1FB, 0x1F1EE}, 5, 18),
      new IosEmoji(new int[] {0x1F1FB, 0x1F1F3}, 5, 19),
      new IosEmoji(new int[] {0x1F1FB, 0x1F1FA}, 5, 20),
      new IosEmoji(new int[] {0x1F1FC, 0x1F1EB}, 5, 21),
      new IosEmoji(new int[] {0x1F1FC, 0x1F1F8}, 5, 22),
      new IosEmoji(new int[] {0x1F1FD, 0x1F1F0}, 5, 23),
      new IosEmoji(new int[] {0x1F1FE, 0x1F1EA}, 5, 24),
      new IosEmoji(new int[] {0x1F1FE, 0x1F1F9}, 5, 25),
      new IosEmoji(new int[] {0x1F1FF, 0x1F1E6}, 5, 26),
      new IosEmoji(new int[] {0x1F1FF, 0x1F1F2}, 5, 27),
      new IosEmoji(new int[] {0x1F1FF, 0x1F1FC}, 5, 28),
      new IosEmoji(new int[] {0x1F3F4, 0xE0067, 0xE0062, 0xE0065, 0xE006E, 0xE0067, 0xE007F}, 12,
          16),
      new IosEmoji(new int[] {0x1F3F4, 0xE0067, 0xE0062, 0xE0073, 0xE0063, 0xE0074, 0xE007F}, 12,
          17),
      new IosEmoji(new int[] {0x1F3F4, 0xE0067, 0xE0062, 0xE0077, 0xE006C, 0xE0073, 0xE007F}, 12,
          18)
  };

  @Override @NonNull public IosEmoji[] getEmojis() {
    return DATA;
  }

  @Override @DrawableRes public int getIcon() {
    return R.drawable.emoji_ios_category_flags;
  }
}
