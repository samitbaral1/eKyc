package com.ekyc.rest.controllers;

import com.ekyc.apiResponse.*;
import com.ekyc.dto.EkycRequestDto;
import com.ekyc.dto.IdentificationDto;
import com.ekyc.dto.UserDetailsDto;
import com.ekyc.dto.UserInformationWithIdentificationDto;
import com.ekyc.enums.IdVerificationType;
import com.ekyc.rest.dao.AuthApiDao;
import com.ekyc.rest.entity.UserDetails;
import com.ekyc.rest.service.KycService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/service")
public class KycController {
    @Autowired
    private KycService kycService;

    @Value("${client_code}")
    private String client_code;
    @Value("${route_key}")
    private String route_key;
    @Value("${client_key}")
    private String client_key;
    @Autowired
    private AuthApiDao authApiDao;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/saveUserData", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDetails> saveUserData(@RequestBody UserDetailsDto inputData) {
        UserInformationWithIdentificationDto userInformationWithIdentification = new UserInformationWithIdentificationDto();
        userInformationWithIdentification.setClient_code("Fill Later");
        userInformationWithIdentification.setOrder_id("fill later after auth api data");
        userInformationWithIdentification.setInputData(inputData);
        inputData.setUserInformation(userInformationWithIdentification);
        UserDetails userDetails = modelMapper.map(inputData, UserDetails.class);
        userDetails = kycService.saveUserInformation(userDetails);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping("getVerificationLink/{userID}")
    public ResponseEntity<String> getVerificationLink(@RequestParam("verificationCardType") IdVerificationType verificationCardType, EkycRequestDto ekycRequestDto, @PathVariable int userID) throws URISyntaxException {
        ekycRequestDto.setClient_key(client_key);
        ekycRequestDto.setRoute_key(route_key);
        ekycRequestDto.setUserID(userID);
        String linkForVerification = kycService.processVerificationLink(verificationCardType, ekycRequestDto);
        return new ResponseEntity<>(linkForVerification, HttpStatus.OK);
    }

    @PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthResponseDto> performAuth(@RequestBody EkycRequestDto ekycRequestDto) throws URISyntaxException, IOException {
        ekycRequestDto.setClient_code(ekycRequestDto.getClient_code() == null ? client_code : ekycRequestDto.getClient_code());
        ekycRequestDto.setRoute_key(ekycRequestDto.getRoute_key() == null ? route_key : ekycRequestDto.getRoute_key());
        AuthResponseDto authResponse = kycService.performAuth(ekycRequestDto);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping(value = "getData/{identificationId}")
    public ResponseEntity<GetDataResponse> getDataOfUser(@RequestBody EkycRequestDto ekycRequestDto, @PathVariable String identificationId){
        ekycRequestDto.setId(identificationId);
        GetDataResponse getDataResponse = kycService.getDataOfUser(ekycRequestDto);
        return new ResponseEntity<>(getDataResponse, HttpStatus.OK);
    }

    @GetMapping(value = "getDataOfUser")
    public GetDataResponseOld getDataResponseTest(){
        return new GetDataResponseOld();
    }
    @PostMapping(value = "getPhotoStatus/{identificationID}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PhotoStatusResponse> getPhotoStatus(@RequestBody EkycRequestDto ekycRequestDto, @PathVariable String identificationID) {
        ekycRequestDto.setClient_code(ekycRequestDto.getClient_code()==null? client_code: ekycRequestDto.getClient_code());
        ekycRequestDto.setRoute_key(ekycRequestDto.getRoute_key()==null? route_key: ekycRequestDto.getRoute_key());
        ekycRequestDto.setId(identificationID);
        PhotoStatusResponse photoStatusResponse = kycService.getPhotoStatus(ekycRequestDto);
        return new ResponseEntity<>(photoStatusResponse, HttpStatus.OK);
    }

    @PostMapping(value = "getImageData/{identificationId}")
    public ResponseEntity<ImageResponse> getImageDataOfUser(@RequestBody EkycRequestDto ekycRequestDto, @PathVariable String identificationId) {
//        AuthResponse authResponse = authApiDao.findby
        ekycRequestDto.setClient_code(client_code);
        ekycRequestDto.setRoute_key(route_key);
        ImageResponse imageResponse = kycService.getImageData(ekycRequestDto);
        return new ResponseEntity<>(imageResponse, HttpStatus.OK);
    }

    @PostMapping(value = "getVideoData/{identificationId}")
    public ResponseEntity<VideoResponse> getVideoDataOfUser(@RequestBody EkycRequestDto ekycRequestDto, @PathVariable String identificationId) {
        ekycRequestDto.setClient_code(client_code);
        ekycRequestDto.setRoute_key(route_key);
        VideoResponse videoResponse = kycService.getVideoData(ekycRequestDto);
        return new ResponseEntity<>(videoResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/identification", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GeneralApiResponse> performIdentification(@RequestBody IdentificationDto identificationDto) throws JsonProcessingException {
        String res = kycService.performIdentification(identificationDto);
        return new ResponseEntity<>(new GeneralApiResponse(), HttpStatus.OK);
    }


}
