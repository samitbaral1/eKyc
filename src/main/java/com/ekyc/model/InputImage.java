package com.ekyc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputImage {
    private String image_type;
    private InputType input_type;
//    Needs to be converted to binary form using base64 encoder
    private String image;
    private String image_token;
    private String masking;
}
