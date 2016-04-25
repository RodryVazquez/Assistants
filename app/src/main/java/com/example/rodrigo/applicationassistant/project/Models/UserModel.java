package com.example.rodrigo.applicationassistant.project.Models;

import com.orm.SugarRecord;

/**
 * Representa al usuario
 */
public class UserModel extends SugarRecord {

    String userId;
    String firstName;
    String lastName;
    String lastName2;
    String phoneNumber;
    String email;


    public UserModel(){}

    /**
     *
     * @param userId
     * @param firstName
     * @param lastName
     * @param lastName2
     * @param phoneNumber
     * @param email
     */
    public UserModel(String userId,String firstName,String lastName, String lastName2, String phoneNumber, String email){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastName2 = lastName2;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
