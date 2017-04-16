package ru.nnmsoftware.telegram.core.types;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stille on 16.04.17.
 */
public class Update {

    @SerializedName("update_id")
    private int updateId;

    private Message message;

    public int getUpdateId() {
        return updateId;
    }

    public Message getMessage() {
        return message;
    }
}
