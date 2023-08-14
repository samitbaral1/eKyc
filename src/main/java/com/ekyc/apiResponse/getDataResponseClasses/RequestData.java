package com.ekyc.apiResponse.getDataResponseClasses;

import com.ekyc.apiResponse.getDataResponseClasses.Orichi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestData {
    private String client_nm;
    private String client_nm_kana;
    private String zip_no;
    private int prefecture_cd;
    private String address_1;
    private String address_2;
    private String address_3;
    private String birth_year;
    private String birth_month;
    private String birth_day;
    private Orichi orochi;
}
