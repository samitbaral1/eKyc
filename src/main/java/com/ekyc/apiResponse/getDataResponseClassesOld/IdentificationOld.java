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
public class IdentificationOld {
    String image_type;
    List<ImageDownloadOld> image_list;
    private String video_url;
    private String duration;
    private List<OCRResultOld> ocr;
    private String authenticity;
    private String judge_result;
    private String masking_result;
}
