package com.help.back.backend.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultUser {

    private int id;
    private String password;
    private String name;
    private String email;
    private String birthday;
    private String phoneNumber;
    private String url;
}
