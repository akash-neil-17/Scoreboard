package com.wlfscsg.GroupCamp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySaveRequestDTO {

    private String activityName;
    private int point;

}
