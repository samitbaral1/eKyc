package com.ekyc.apiResponse.getDataResponseNeededClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaceAuthentication{
    public String image_type;
    public String imgae_pattern;
    public String selfie_action_type;
    public ArrayList<ImageList> image_list;
    public String video_url;
    public String duration;
    public int result;
    public String score;
}