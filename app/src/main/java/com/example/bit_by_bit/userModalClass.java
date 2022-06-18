package com.example.bit_by_bit;

public class userModalClass {
    String profile_name, about, uid;

    public userModalClass(String profile_name, String about, String uid) {
        this.profile_name = profile_name;
        this.about = about;
        this.uid = uid;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
