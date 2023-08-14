package com.ekyc.dto;

import com.ekyc.rest.entity.UserInformationWithIdentification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
    private String lastname;
    private String firstname;
    private String middlename;
    private String firstkana;
    private String lastkana;
    private int gender;             // 0 others, 1 male, 2 female
    private String birthday;
    private String zipcode;
    private String prefectures;
    private String city;
    private String street;
    private String building;
    private String prefkana;
    private String citykana;
    private String streetkana;
    private String buildingkana;
    private UserInformationWithIdentificationDto userInformation;
}
