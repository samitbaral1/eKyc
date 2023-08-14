package com.ekyc.apiResponse.getDataResponseClassesOld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplementaryResultOld {
    String image_type;
    String url;
    List<String> url_info;
    List<String> regist_data;
    List<OCRResultOld> ocr;
    String judge_result;
    String period_result;
}
