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
    private int publicType;
    private int scheduleType;
    private double latitude;
    private double longitude;
    private int isCompleted;
    private int userId;
    private int groupId;
    private String color;

    @Builder
    public Schedule(String title, String content, String startTime, String endTime) {
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
