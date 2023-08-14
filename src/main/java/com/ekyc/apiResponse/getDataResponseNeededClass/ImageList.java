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
public class ImageList{
    public String url;
    public ArrayList<String> url_info;
    public String format;
}
