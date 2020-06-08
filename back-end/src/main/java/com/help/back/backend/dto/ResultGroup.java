package com.help.back.backend.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ResultGroup {

    private int id;
    private String name;
    private String url;

    @Builder
    public ResultGroup(int id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
