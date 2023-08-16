package com.ekyc.rest.service;

import com.ekyc.apiResponse.*;
import com.ekyc.enums.IdVerificationType;
import com.ekyc.enums.ImageCodes;
import com.ekyc.dto.EkycRequestDto;
import com.ekyc.dto.IdentificationDto;
import com.ekyc.rest.entity.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface KycService {
    UserDetails saveUserInformation(UserDetails inputData);
    String processVerificationLink(IdVerificationType cardType, EkycRequestDto ekycRequestDto) throws URISyntaxException;
    UriComponentsBuilder generateVerificationLink(EkycRequestDto ekycRequestDto, List<ImageCodes> imageCodes) throws URISyntaxException;
    AuthResponseDto performAuth(EkycRequestDto authRequest) throws URISyntaxException, IOException;
    GetDataResponse getDataOfUser(EkycRequestDto ekycRequestDto);
    PhotoStatusResponse getPhotoStatus(EkycRequestDto ekycRequestDto);
    Resource getImageData(EkycRequestDto ekycRequestDto);
    Resource getVideoData(EkycRequestDto ekycRequestDto);
    String performIdentification(IdentificationDto identificationDto) throws JsonProcessingException;
}
