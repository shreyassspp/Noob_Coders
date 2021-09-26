package com.example.noob_coders;

public class Profile {
    String email,field;

    public Profile(String email,String field)
    {
        this.email = email;
        this.field = field;
    }

    public String getEmail() {
        return email;
    }

    public String getField() {
        return field;
    }
}
