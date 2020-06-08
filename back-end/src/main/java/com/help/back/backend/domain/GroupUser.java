package com.help.back.backend.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class GroupUser {
    private int ugId;
    private int userId;
    private int groupId;
    private int isHost;

    private User user;

}
