package com.telegram.poopbot.model;

public class PoopCount {

    private String firstName;
    private String lastName;
    private String userName;
    private long count;

    public PoopCount(String firstName, String lastName, String userName, long count) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.count = count;
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

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }//------------------------------------------------------------------------------------------------------------------------------------

}