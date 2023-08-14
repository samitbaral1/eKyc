package com.ekyc.apiResponse.getDataResponseClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestJsonData {
    private List<Identification> identification;
    private List<MyNumberResult> mynumber_result;
    private List<FaceAuthentication> face_auth;
    private List<SupplementaryResult> supplementary_result;
    private List<AccountInfoResult> account_info_result;
}
