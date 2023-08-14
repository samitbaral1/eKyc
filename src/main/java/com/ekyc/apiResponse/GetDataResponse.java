package com.ekyc.apiResponse;

import com.ekyc.apiResponse.getDataResponseClasses.RequestData;
import com.ekyc.apiResponse.getDataResponseClasses.RequestJsonData;
import com.ekyc.apiResponse.getDataResponseClasses.ScrutinyData;
import com.ekyc.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetDataResponse {
    private Status status;
    private RequestData request_data;
    private String regist_flg;
    private RegistrationData regist_data;
    private RequestJsonData request_data_json;
    private ScrutinyData scrutinyData;
}
