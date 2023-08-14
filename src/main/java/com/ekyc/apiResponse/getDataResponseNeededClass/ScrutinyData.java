package com.ekyc.apiResponse.getDataResponseNeededClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScrutinyData{
    public int scrutiny_status;
    public String scrutiny_comment;
    public String scrutiny_date;
    public String scrutiny_user_nm;
}