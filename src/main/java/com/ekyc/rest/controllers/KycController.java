package com.ekyc.rest.controllers;

import com.ekyc.model.AuthRequest;
import com.ekyc.model.GeneralApiResponse;
import com.ekyc.model.IdentificationDto;
import com.ekyc.rest.service.KycService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/service")
public class KycController {
    @Autowired
    private KycService kycService;

    @PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GeneralApiResponse> performAuth(@RequestBody AuthRequest authRequest) {
        GeneralApiResponse generalApiResponse = kycService.performAuth(authRequest);
        return new ResponseEntity<>(generalApiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/identification", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GeneralApiResponse> performIdentification(@RequestBody IdentificationDto identificationDto) throws JsonProcessingException {
        String res = kycService.performIdentification(identificationDto);
        return new ResponseEntity<>(new GeneralApiResponse(), HttpStatus.OK);
    }
}
