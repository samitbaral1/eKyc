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
public class MyNumberResult {
    String image_type;
    List<ImageDownload> image_list;
    String mynumber;
    List<OCRResult> ocr;
    String judge_result;
}
