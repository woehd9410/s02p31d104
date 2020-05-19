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
    private char title;
    private Date start_time;
    private Date end_time;
    private char address;
    private int sche_type;
    private double latitude;
    private double longitude;
    private char user_id;
}