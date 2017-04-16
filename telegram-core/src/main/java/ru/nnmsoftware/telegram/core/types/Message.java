package ru.nnmsoftware.telegram.core.types;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by stille on 16.04.17.
 */
public class Message {

    @SerializedName("message_id")
    private int messageId;

    private User from;

    @SerializedName("forward_from")
    private User forwardFrom;

    private String text;

    public int getMessageId() {
        return messageId;
    }

    public User getFrom() {
        return from;
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public String getText() {
        return text;
    }
}
