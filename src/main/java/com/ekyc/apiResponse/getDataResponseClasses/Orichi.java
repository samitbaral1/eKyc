package com.ekyc.apiResponse.getDataResponseClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orichi {
    private String address;
    private String zip_code;
    private String prefecture;
    private String prefecture_cd;
    private String city;
    private String city2;
    private String city_block;
    private String street;
    private String building;
    private String bldg;
    private String floor;
    private String src_prefectures;
    private String src_city;
    private String src_city_block;
    private String src_street;
    private String src_building;
    private String src_bldg;
    private String src_floor;
    private String src_house_number;
    private String status_code;
    private String status;
}
