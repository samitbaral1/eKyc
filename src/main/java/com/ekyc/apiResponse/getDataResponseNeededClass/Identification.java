package com.ekyc.apiResponse.getDataResponseNeededClass;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Identification{
    public String image_type;
    public String video_url;
    public String duration;
    public ArrayList<ImageList> image_list;
    public ArrayList<OCRResult> ocr;
    public String authenticity;
    public String judge_result;
    public JudgeResultDetails judge_result_detail;
}
