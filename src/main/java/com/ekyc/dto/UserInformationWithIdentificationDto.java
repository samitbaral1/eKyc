package com.ekyc.dto;

import com.ekyc.rest.entity.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationWithIdentificationDto {
    private String order_id;
    private String client_code;
    private UserDetailsDto inputData;

}
