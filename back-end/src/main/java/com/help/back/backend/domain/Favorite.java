package com.help.back.backend.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@NoArgsConstructor
public class Favorite {
    private int toUserId;
    private int fromUserId;
}



