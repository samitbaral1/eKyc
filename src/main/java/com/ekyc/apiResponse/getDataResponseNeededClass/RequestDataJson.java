package com.ekyc.apiResponse.getDataResponseNeededClass;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDataJson{
    public ArrayList<Identification> identification;
    public ArrayList<Object> mynumber_result;
    public ArrayList<FaceAuthentication> face_auth;
    public String selfie_action_judge;
    public ArrayList<Object> supplementary_result;
    public ArrayList<Object> account_info_result;
    public ArrayList<Object> ic_result;
    public BankAuth bank_auth;
}
