package com.telegram.poopbot.repository;

import com.telegram.poopbot.model.Poop;
import com.telegram.poopbot.model.PoopCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PoopRepository extends JpaRepository<Poop, Long> {

    @Query("SELECT new com.telegram.poopbot.model.PoopCount(user.firstName, user.lastName, user.userName, COUNT(poop.id)) " +
        "FROM Poop AS poop " +
            "INNER JOIN PoopUser AS user ON poop.userId = user.id " +
        "WHERE poop.chatId = ?1 " +
        "GROUP BY user.firstName, user.lastName, user.userName " +
        "ORDER BY COUNT(poop.id) DESC")
    List<PoopCount> getLeaderboardByChatId(long chatId);

    @Query("SELECT new com.telegram.poopbot.model.PoopCount(user.firstName, user.lastName, user.userName, COUNT(poop.id)) " +
            "FROM Poop AS poop " +
            "INNER JOIN PoopUser AS user ON poop.userId = user.id " +
            "WHERE poop.date >= ?1 AND poop.date <= ?2 AND poop.chatId = ?3 " +
            "GROUP BY user.firstName, user.lastName, user.userName " +
            "ORDER BY COUNT(poop.id) DESC")
    List<PoopCount> getLeaderboardByChatIdAndDate(Date startDate, Date endDate, long chatId);

}