package com.ekyc.apiResponse.getDataResponseClassesOld;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OCRResultOld {
    private String name1;
    private String name2;
    private String address1;
    private String address2;
    private String birth;
    private String expire;
    private String issue;
    private String gender;
    private String domicile;
    private String status_code;
    private String transaction_id;
    private String result_code;
    private String result_message;
    private String image_type;
    private String sub_image_type;

    private String license_number;
    private MaskRect mask_rect;
    private String forgery_check_score;
    private String rescue;
    private String score;
    private String house_number;
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
    private String name;

    private class MaskRect {
        private int x;
        private int y;
        private int width;
        private int height;
    }
}
