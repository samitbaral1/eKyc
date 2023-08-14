package com.ekyc.apiResponse.getDataResponseClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoResult {
    String image_type;
    List<String> url_info;
    String url;
    List<OCRResult> ocr;
}
