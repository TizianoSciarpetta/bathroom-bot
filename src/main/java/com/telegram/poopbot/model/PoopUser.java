package com.telegram.poopbot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.telegram.telegrambots.meta.api.objects.User;

@Entity
public class PoopUser {

    @Id
    private long id;

    private String firstName;
    private String lastName;
    private String userName;

    public PoopUser() {
        //No-args constructor
    }//------------------------------------------------------------------------------------------------------------------------------------

    public PoopUser(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
    }//------------------------------------------------------------------------------------------------------------------------------------

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public String getUsername() {
        return userName;
    }
    public void setUsername(String userName) {
        this.userName = userName;
    }//------------------------------------------------------------------------------------------------------------------------------------

}