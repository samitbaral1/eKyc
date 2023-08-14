package com.ekyc.apiResponse;

import com.ekyc.apiResponse.getDataResponseNeededClass.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetDataResponse {
        public Status status;
        public RequestData request_data;
        public String regist_flg;
        public RegistrationData regist_data;
        public RequestDataJson request_data_json;
        public ArrayList<Object> corporation_result;
        public ArrayList<Object> other_result;
        public ScrutinyData scrutiny_data;
        public Object attribute;
        public ScreeningData screening_data;

}
