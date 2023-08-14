package com.ekyc.dto;

import com.ekyc.dto.EkycRequestDto;
import com.ekyc.model.InputDetails;
import com.ekyc.model.InputImage;
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
    private EkycRequestDto authRequest;
    private String api_token;
    private String id;
    private List<InputImage> inputImageList;
    private InputDetails inputDetails;
    private String authenticity;
}
