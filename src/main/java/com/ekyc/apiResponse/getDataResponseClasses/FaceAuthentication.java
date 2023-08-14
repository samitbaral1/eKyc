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
public class FaceAuthentication {
    private String image_type;
    private String imgae_pattern;
    private String selfie_action_type;
    private List<ImageDownload> image_list;
    private String video_url;
    private String duration;
    private String result;
    private String score;
}
