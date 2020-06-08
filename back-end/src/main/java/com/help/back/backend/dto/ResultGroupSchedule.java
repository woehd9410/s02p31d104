package com.help.back.backend.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ResultGroupSchedule {
    private int id;
    private String name;
    private String email;
    private String birthday;
    private String title;
    private String content;
    private String startTime;
    private String endTime;
    private String address;
    private int publicType;
    private int scheduleType;
    private double latitude;
    private double longitude;
    private int isCompleted;

    @Builder
    public ResultGroupSchedule(int id, String name, String email, String birthday,
                               String title, String content, String startTime, String endTime, String address,
                               int publicType, int scheduleType, double latitude, double longitude, int isCompleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.publicType = publicType;
        this.scheduleType = scheduleType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isCompleted = isCompleted;
    }
}
