package com.ekyc.apiResponse.getDataResponseNeededClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningData{
    public String screening_type;
    public int screening_status;
    public String screening_detail;
}