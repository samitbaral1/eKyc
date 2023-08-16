package com.ekyc.apiResponse;

import com.ekyc.apiResponse.getDataResponseNeededClass.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoStatusResponse {
    private Status status;
    private int photo_status;
}
