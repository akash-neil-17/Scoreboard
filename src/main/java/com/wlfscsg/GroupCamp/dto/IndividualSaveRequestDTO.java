package com.wlfscsg.GroupCamp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualSaveRequestDTO {

    private String individualName;
    private int patrolUnit;
    private String patrolName;
    private String mobileNumber;

}
