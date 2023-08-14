package com.ekyc.apiResponse;

import com.ekyc.apiResponse.getDataResponseClassesOld.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private Status status;
    public String id;
    public String datetime;
    List<String> api_tokens;
    List<String> image_tokens;
}
