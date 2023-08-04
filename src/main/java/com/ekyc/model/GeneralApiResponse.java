package com.ekyc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralApiResponse {
    private Status status;
    private String id;
    private String datetime;
    private List<String> api_tokens;
    private List<String> image_tokens;
}
