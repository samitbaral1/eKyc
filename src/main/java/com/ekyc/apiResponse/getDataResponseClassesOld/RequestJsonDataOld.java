package com.ekyc.apiResponse.getDataResponseClassesOld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestJsonDataOld {
    private List<IdentificationOld> identification;
    private List<MyNumberResultOld> mynumber_result;
    private List<FaceAuthenticationOld> face_auth;
    private List<SupplementaryResultOld> supplementary_result;
    private List<AccountInfoResultOLD> account_info_result;
}
