package com.ekyc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationDto {
    private AuthRequest authRequest;
    private String api_token;
    private String id;
    private List<InputImage> inputImageList;
    private InputDetails inputDetails;
    private String authenticity;
}
