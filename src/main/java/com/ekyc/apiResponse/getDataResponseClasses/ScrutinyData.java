package com.ekyc.apiResponse.getDataResponseClasses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScrutinyData {
    private String scrutinyData;
    private String scrutinyStatus;
    private String scrutinyComment;
    private String scrutinyDate;
    private String scrutinyUserName;
}
