package com.ekyc.apiResponse;

import com.ekyc.apiResponse.getDataResponseNeededClass.*;
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
    private RequestData request_data;
    private String regist_flg;
    private RegistrationData regist_data;
    private RequestDataJson request_data_json;
    private ScrutinyData scrutinyData;
}
