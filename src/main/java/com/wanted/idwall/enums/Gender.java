package com.wanted.idwall.enums;

public enum Gender {

    M("Male"),
    F("Female");

    private String gender;

    public String getGender(){
        return gender;
    }

    Gender(String gender){
        this.gender = gender;
    }
}
