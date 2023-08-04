package com.ekyc.rest.service;

import com.ekyc.model.AuthRequest;
import com.ekyc.model.GeneralApiResponse;
import com.ekyc.model.IdentificationDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface KycService {
    GeneralApiResponse performAuth(AuthRequest authRequest);
    String performIdentification(IdentificationDto identificationDto) throws JsonProcessingException;
}
