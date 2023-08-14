package com.ekyc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationData {
    private String client_nm;
    private String birth_year;
    private String birth_month;
    private String birth_day;
    private String zip_no;
    private int pref_cd;
    private String city;
    private String town;
    private String building;
    private String expire;
}
