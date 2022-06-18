package com.example.bit_by_bit;

public class chatLayoutModal {
    int image;
    String fullname, last_message, last_time;

    public chatLayoutModal(int image, String fullname, String last_message, String last_time) {
        this.image = image;
        this.fullname = fullname;
        this.last_message = last_message;
        this.last_time = last_time;
    }

    public int getImage() {
        return image;
    }

    public String getFullname() {
        return fullname;
    }

    public String getLast_message() {
        return last_message;
    }

    public String getLast_time() {
        return last_time;
    }
}
