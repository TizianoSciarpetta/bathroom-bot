package com.telegram.poopbot;

import com.telegram.poopbot.service.PoopLeaderboardService;
import com.telegram.poopbot.service.PoopService;
import net.fellbaum.jemoji.Emoji;
import net.fellbaum.jemoji.EmojiManager;
import net.fellbaum.jemoji.Emojis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Set;

@Component
public class PoopBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final String token;
    private final TelegramClient telegramClient;

    /** Services **/
    private final PoopService poopService;
    private final PoopLeaderboardService poopLeaderboardService;

    public PoopBot(@Value("${telegram.bot.token}") String token, PoopService poopService, PoopLeaderboardService poopLeaderboardService) {
        this.token = token;
        this.telegramClient = new OkHttpTelegramClient(getBotToken());
        this.poopService = poopService;
        this.poopLeaderboardService = poopLeaderboardService;
    }//------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void consume(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            messageReceived(message);
        }else if(update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            callbackQueryReceived(callbackQuery);
        }
    }//------------------------------------------------------------------------------------------------------------------------------------

    private void messageReceived(Message message) {
        String text = message.getText();
        if(!EmojiManager.containsEmoji(text)) {
            return;
        }

        Set<Emoji> emojis = EmojiManager.extractEmojis(text);
        if(emojis.stream().anyMatch(Emojis.PILE_OF_POO::equals)) {
            SendMessage sendMessage = poopService.poopEmojiReceived(message);
            if(sendMessage != null) {
                send(sendMessage);
            }
        }
    }//------------------------------------------------------------------------------------------------------------------------------------

    private void callbackQueryReceived(CallbackQuery callbackQuery) {
        long chatId = callbackQuery.getMessage().getChatId();

        SendMessage sendMessage = null;

        switch(callbackQuery.getData()) {
            case "get_daily_leaderboard":
                sendMessage = poopLeaderboardService.getDailyLeaderboard(chatId);
                break;
            case "get_monthly_leaderboard":
                sendMessage = poopLeaderboardService.getMonthlyLeaderboard(chatId);
                break;
            case "get_all_time_leaderboard":
                sendMessage = poopLeaderboardService.getAllTimeLeaderboard(chatId);
                break;
            default:
                break;
        }

        if(sendMessage != null) {
            send(sendMessage);
        }
    }//------------------------------------------------------------------------------------------------------------------------------------

    private void send(SendMessage message) {
        try {
            telegramClient.execute(message);
        }catch(TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }//------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public String getBotToken() {
        return token;
    }//------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }//------------------------------------------------------------------------------------------------------------------------------------

}