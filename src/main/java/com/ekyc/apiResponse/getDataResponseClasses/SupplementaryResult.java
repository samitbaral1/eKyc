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
public class SupplementaryResult {
    String image_type;
    String url;
    List<String> url_info;
    List<String> regist_data;
    List<OCRResult> ocr;
    String judge_result;
    String period_result;
}
