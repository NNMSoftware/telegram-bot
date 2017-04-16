package ru.nnmsoftware.telegram.core.types;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stille on 16.04.17.
 */
public class User {

    private int id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private String username;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
}
