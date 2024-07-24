package com.telegram.poopbot.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Poop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poop_seq")
    @SequenceGenerator(name = "poop_seq", sequenceName = "poop_seq",  allocationSize = 1)
    private long id;

    private Date date;
    private long chatId;
    private long userId;

    public Poop() {
        //No-args constructor
    }//------------------------------------------------------------------------------------------------------------------------------------

    public Poop(long userId, long chatId, Date date) {
        this.userId = userId;
        this.chatId = chatId;
        this.date = date;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public long getChatId() {
        return chatId;
    }
    public void setChatId(long chatId) {
        this.chatId = chatId;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }//------------------------------------------------------------------------------------------------------------------------------------

}