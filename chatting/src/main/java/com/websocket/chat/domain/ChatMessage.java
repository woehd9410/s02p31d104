package com.websocket.chat.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ChatMessage implements Serializable {

    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    private int roomId;
    private String sender;
    private String email;
    private String message;
}
