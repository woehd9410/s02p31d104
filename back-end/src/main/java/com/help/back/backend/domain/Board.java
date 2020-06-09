package com.help.back.backend.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Board {
    private int id;
    private String title;
    private String createdTime;
    private String updatedTime;
    private String content;
    private String publicAuth;
    private int isPublic;
    private int userId;

}
