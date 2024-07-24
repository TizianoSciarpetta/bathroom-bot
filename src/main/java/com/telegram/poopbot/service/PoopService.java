package com.telegram.poopbot.service;

import com.telegram.poopbot.model.Poop;
import com.telegram.poopbot.repository.PoopRepository;
import net.fellbaum.jemoji.Emojis;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.time.Instant;
import java.util.Date;

@Service
public class PoopService {

    private final PoopRepository poopRepository;

    private final PoopUserService poopUserService;

    public PoopService(PoopUserService poopUserService, PoopRepository poopRepository) {
        this.poopUserService = poopUserService;
        this.poopRepository = poopRepository;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public SendMessage poopEmojiReceived(Message message) {
        int unixSeconds = message.getDate();
        long chatId = message.getChatId();
        User user = message.getFrom();

        // Async - check for user updates
        poopUserService.checkAndSave(user);

        Date date = Date.from(Instant.ofEpochSecond(unixSeconds));
        System.out.println(date);

        // Save and send a message
        Poop poop = new Poop(user.getId(), chatId, date);
        this.poopRepository.save(poop);

        return buildSendMessage("Shit has been added successfully " + Emojis.CHECK_MARK_BUTTON.getEmoji(), chatId);
    }//------------------------------------------------------------------------------------------------------------------------------------

    private SendMessage buildSendMessage(String text, long chatId) {
        InlineKeyboardButton dailyLeaderboardButton = InlineKeyboardButton.builder()
                .text(Emojis.TROPHY.getEmoji() + " Daily")
                .callbackData("get_daily_leaderboard")
                .build();

        InlineKeyboardButton monthlyLeaderboardButton = InlineKeyboardButton.builder()
                .text(Emojis.TROPHY.getEmoji() + " Monthly")
                .callbackData("get_monthly_leaderboard")
                .build();

        InlineKeyboardButton allTimeLeaderboardButton = InlineKeyboardButton.builder()
            .text(Emojis.TROPHY.getEmoji() + " All Time")
            .callbackData("get_all_time_leaderboard")
            .build();

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
            .keyboardRow(new InlineKeyboardRow(dailyLeaderboardButton, monthlyLeaderboardButton))
            .keyboardRow(new InlineKeyboardRow(allTimeLeaderboardButton))
            .build();

        SendMessage message = SendMessage.builder()
            .chatId(chatId)
            .text(text)
            .replyMarkup(inlineKeyboardMarkup)
            .build();

        return message;
    }//------------------------------------------------------------------------------------------------------------------------------------

}