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
public class Identification {
    String image_type;
    List<ImageDownload> image_list;
    private String video_url;
    private String duration;
    private List<OCRResult> ocr;
    private String authenticity;
    private String judge_result;
    private String masking_result;
}
