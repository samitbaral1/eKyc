package com.ekyc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EkycRequestDto {
    private String id;
    private String client_code;
    private String client_key;
    private String route_key;
    private int api_num;
    private int image_num;
    private String masking;
    private String hash;
    private int userID;
}
