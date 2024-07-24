package com.telegram.poopbot.service;

import com.telegram.poopbot.model.PoopCount;
import com.telegram.poopbot.repository.PoopRepository;
import net.fellbaum.jemoji.Emojis;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

@Service
public class PoopLeaderboardService {

    private final PoopRepository poopRepository;

    public PoopLeaderboardService(PoopRepository poopRepository) {
        this.poopRepository = poopRepository;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public SendMessage getDailyLeaderboard(long chatId) {
        LocalDate today = LocalDate.now(ZoneOffset.UTC);

        Date startDate = Date.from(today.atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endDate = Date.from(today.atTime(23, 59, 59, 999_999_999).atZone(ZoneOffset.UTC).toInstant());

        List<PoopCount> poopCountList = poopRepository.getLeaderboardByChatIdAndDate(startDate, endDate, chatId);
        return buildLeaderboard(poopCountList, "Daily", chatId);
    }//------------------------------------------------------------------------------------------------------------------------------------

    public SendMessage getMonthlyLeaderboard(long chatId) {
        LocalDate today = LocalDate.now(ZoneOffset.UTC);

        Date startMonthDate = Date.from(today.withDayOfMonth(1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endMonthDate = Date.from(today.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59, 999_999_999).atZone(ZoneOffset.UTC).toInstant());

        List<PoopCount> poopCountList = poopRepository.getLeaderboardByChatIdAndDate(startMonthDate, endMonthDate, chatId);
        return buildLeaderboard(poopCountList, "Monthly", chatId);
    }//------------------------------------------------------------------------------------------------------------------------------------

    public SendMessage getAllTimeLeaderboard(long chatId) {
        List<PoopCount> poopCountList = poopRepository.getLeaderboardByChatId(chatId);
        return buildLeaderboard(poopCountList, "All Time", chatId);
    }//------------------------------------------------------------------------------------------------------------------------------------

    private SendMessage buildLeaderboard(List<PoopCount> poopCountList, String title, long chatId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Emojis.TROPHY.getEmoji()).append(" *").append(title).append(" Leaderboard*").append("\n\n");

        for(int i = 0; i < poopCountList.size(); i++) {
            PoopCount poopCount = poopCountList.get(i);

            if(i == 0) {
                stringBuilder.append(Emojis.FIRST_PLACE_MEDAL.getEmoji()).append(" ");
            }else if(i == 1) {
                stringBuilder.append(Emojis.SECOND_PLACE_MEDAL.getEmoji()).append(" ");
            }else if(i == 2) {
                stringBuilder.append(Emojis.THIRD_PLACE_MEDAL.getEmoji()).append(" ");
            }

            stringBuilder.append(poopCount.getFirstName()).append(" ").append(poopCount.getLastName()).append("   *").append(poopCount.getCount()).append("*").append(Emojis.PILE_OF_POO.getEmoji()).append("\n");
        }

        return buildSendMessage(stringBuilder.toString(), chatId);
    }//------------------------------------------------------------------------------------------------------------------------------------

    private SendMessage buildSendMessage(String text, long chatId) {
        return SendMessage.builder()
            .chatId(chatId)
            .text(text)
            .parseMode("MarkdownV2")
            .build();
    }//------------------------------------------------------------------------------------------------------------------------------------

}