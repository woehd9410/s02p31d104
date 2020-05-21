package com.help.back.backend.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Schedule {

    private int id;
    private String content;
    private String title;
    private String startTime;
    private String endTime;
    private String address;
    private int type;
    private double latitude;
    private double longitude;
    private int userId; 
}