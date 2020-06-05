package com.websocket.chat.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Group implements Serializable {
    private int roomId;
    private String name;
}
