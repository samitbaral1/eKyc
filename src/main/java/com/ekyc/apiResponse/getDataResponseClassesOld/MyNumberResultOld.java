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
public class MyNumberResultOld {
    String image_type;
    List<ImageDownloadOld> image_list;
    String mynumber;
    List<OCRResultOld> ocr;
    String judge_result;
}
