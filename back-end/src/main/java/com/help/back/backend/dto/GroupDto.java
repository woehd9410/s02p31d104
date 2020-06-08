package com.help.back.backend.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class GroupDto {

    private String name;
    private List<String> members;
}
