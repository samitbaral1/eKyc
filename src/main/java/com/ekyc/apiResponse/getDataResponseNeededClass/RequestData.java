package com.ekyc.apiResponse.getDataResponseNeededClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestData{
    public String client_nm;
    public String client_nm_kana;
    public String client_nm_eiji;
    public String gender;
    public String zip_no;
    public String prefecture_cd;
    public String address_1;
    public String address_2;
    public String address_3;
    public String address;
    public String birth_year;
    public String birth_month;
    public String birth_day;
    public Orochi orochi;
    public String generic_fld1;
    public String generic_fld2;
    public String generic_fld3;
    public String generic_fld4;
    public String generic_fld5;
}
