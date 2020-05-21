package com.help.back.backend.domain;

import java.util.Date;

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
    private String start_time;
    private String end_time;
    private String address;
    private int type;
    private double latitude;
    private double longitude;
    private int user_id;
}