package com.example.rodrigo.applicationassistant.project.Models;

import com.orm.SugarRecord;

/**
 * Representa un asistente
 */
public class AssistanceModel extends SugarRecord {

    int userId;
    String address;
    int phoneNumber;
    String email;
    String comments;
    String registerDateTimeStamp;
    boolean active;
    String userSectionId;

    public AssistanceModel(){}

    /**
     *
     * @param address
     * @param phoneNumber
     * @param email
     * @param comments
     * @param registerDateTimeStamp
     * @param active
     * @param userSectionId
     */
    public AssistanceModel(String address, int phoneNumber, String email, String comments, String registerDateTimeStamp, boolean active, String userSectionId){
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.comments = comments;
        this.registerDateTimeStamp = registerDateTimeStamp;
        this.active = active;
        this.userSectionId = userSectionId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRegisterDateTimeStamp() {
        return registerDateTimeStamp;
    }

    public void setRegisterDateTimeStamp(String registerDateTimeStamp) {
        this.registerDateTimeStamp = registerDateTimeStamp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserSectionId() {
        return userSectionId;
    }

    public void setUserSectionId(String userSectionId) {
        this.userSectionId = userSectionId;
    }
}
