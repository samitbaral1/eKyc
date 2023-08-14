package com.ekyc.apiResponse;

import com.ekyc.apiResponse.getDataResponseClassesOld.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetDataResponseOld {
    private Status status;
    private RequestDataOld request_data;
    private String regist_flg;
    private RegistrationData regist_data;
    private RequestJsonDataOld request_data_json;
    private ScrutinyDataOld scrutinyData;
}
