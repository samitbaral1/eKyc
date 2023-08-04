package com.ekyc.rest.service;

import com.ekyc.model.*;
import com.ekyc.utils.RandomGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
//import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KycServiceImpl implements KycService {
    @Override
    public GeneralApiResponse performAuth(AuthRequest authRequest) {
        GeneralApiResponse generalApiResponse = new GeneralApiResponse();
//        JSONObject jsonObject = new JSONObject();
        Map<String, String> formData = new HashMap<>();
        formData.put("client_Code", authRequest.getClient_code());
        formData.put("route_Key", authRequest.getRoute_key());
        formData.put("api_Num", String.valueOf(authRequest.getApi_num()));
        formData.put("image_Num", String.valueOf(authRequest.getImage_num()));
        String requestBody = formData.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost httpPost = new HttpPost("https://dev-ap-dtrust.double-std.com/service/api/auth");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(new StringEntity(requestBody));
            HttpResponse response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generalApiResponse;
    }

    @Override
    public String performIdentification(IdentificationDto identificationDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> formData = new HashMap<>();
        formData.put("client_Code", identificationDto.getAuthRequest().getClient_code());
        formData.put("route_Key", identificationDto.getAuthRequest().getRoute_key());
        formData.put("id", identificationDto.getId());
        formData.put("authenticity", identificationDto.getAuthenticity());

        List<InputImage> inputImageList = new ArrayList<>();
        for (int i = 0; i < identificationDto.getInputImageList().size(); i++) {
            InputImage inputImage = new InputImage();
            InputImage inputImage1 = identificationDto.getInputImageList().get(i);
            inputImage.setImage_type(inputImage1.getImage_type());
            inputImage.setInput_type(new InputType(inputImage1.getInput_type().getImage(), inputImage1.getInput_type().getToken()));
            inputImage.setImage(inputImage1.getImage());
            inputImage.setImage_token(inputImage1.getImage_token());
            inputImage.setMasking(inputImage1.getMasking());
            inputImageList.add(inputImage);
        }
        String inputImageListJson = objectMapper.writeValueAsString(inputImageList);
        formData.put("inputImageList", inputImageListJson);
        formData.put("name", identificationDto.getInputDetails().getName());
        formData.put("birthday", identificationDto.getInputDetails().getBirthday());
        formData.put("address", identificationDto.getInputDetails().getAddress());
        InputDetails inputDetails = new InputDetails();
        inputDetails.setName(identificationDto.getInputDetails().getName());
        inputDetails.setBirthday(identificationDto.getInputDetails().getBirthday());
        inputDetails.setAddress(identificationDto.getInputDetails().getAddress());
        formData.put("input_data", objectMapper.writeValueAsString(inputDetails));
        String requestBody = formData.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost httpPost = new HttpPost("https://dev-ap-dtrust.double-std.com/service/api/identification");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(new StringEntity(requestBody));
            HttpResponse response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


//
//    HttpRequest httpRequest = HttpRequest
//            .newBuilder()
//            .uri(new URI("https://dev-ap-dtrust.double-std.com/service/api/auth"))
//            .header("Content-Type", "application/x-www-form-urlencoded")
//            .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
////                    .header("Accept","application/json")
//
//            .build();
//    HttpResponse response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

