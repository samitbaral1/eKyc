package com.ekyc.rest.service;

import com.ekyc.apiResponse.AuthResponseDto;
import com.ekyc.apiResponse.GetDataResponse;
import com.ekyc.apiResponse.PhotoStatusResponse;
import com.ekyc.dto.EkycRequestDto;
import com.ekyc.dto.IdentificationDto;
import com.ekyc.enums.IdVerificationType;
import com.ekyc.enums.ImageCodes;
import com.ekyc.model.InputDetails;
import com.ekyc.model.InputImage;
import com.ekyc.model.InputType;
import com.ekyc.rest.dao.UserDetailsDao;
import com.ekyc.rest.entity.UserDetails;
import com.ekyc.utils.Card_ImageCode_Mapping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KycServiceImpl implements KycService {
    @Value("${wsHost}")
    private String wsHost;
    @Value("${phpAuthApi}")
    private String phpAuthApi;
    @Value("${eKycVerificationBaseUrl}")
    private String ekycVerificationBaseUrl;
    @Value("${phpGetDataApi}")
    private String phpGetDataApi;
    @Value("${phpGetImageDataApi}")
    private String phpGetImageDataApi;
    @Value("${phpGetVideoDataApi}")
    private String phpGetVideoDataApi;

    @Autowired
    private UserDetailsDao userDetailsDao;
    @Autowired
    private ObjectMapper objectMapper;


    public UserDetails saveUserInformation(UserDetails userInputData) {
        UserDetails savedUserDetails = userDetailsDao.save(userInputData);
        return savedUserDetails;
    }

    @Override
    public String processVerificationLink(IdVerificationType cardType, EkycRequestDto ekycRequestDto) throws URISyntaxException {
        List<ImageCodes> imageCodes = Card_ImageCode_Mapping.getCodesWithCardName().get(cardType);
        ekycRequestDto.setImage_num(imageCodes.size());
        ekycRequestDto.setHash(Card_ImageCode_Mapping.getImageHashCode().get(cardType));
        ekycRequestDto.setApi_num(imageCodes.size());
        UriComponentsBuilder verificationLinkGenerated = generateVerificationLink(ekycRequestDto, imageCodes);
        JSONObject jsonObject = new JSONObject(ekycRequestDto);
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(wsHost + "service/auth");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            StringEntity stringEntity = new StringEntity(jsonObject.toString());
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = rd.readLine())) {
                content.append(line);
            }
            UserDetails userDetails = userDetailsDao.getById(ekycRequestDto.getUserID());
            JSONObject jsonObject1 = new JSONObject(content.toString());
            userDetails.getUserInformation().setOrder_id(jsonObject1.optString("id"));
            userDetailsDao.save(userDetails);
            verificationLinkGenerated.queryParam("id", jsonObject1.optString("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verificationLinkGenerated.build().toString();
    }

    @Override
    public UriComponentsBuilder generateVerificationLink(EkycRequestDto ekycRequestDto, List<ImageCodes> imageCodes) throws URISyntaxException {
        UriComponentsBuilder uriComponents = UriComponentsBuilder.fromUri(new URI(ekycVerificationBaseUrl)).queryParam("client_key", ekycRequestDto.getClient_key()).queryParam("route_key", ekycRequestDto.getRoute_key());
        for (int i = 0; i < ekycRequestDto.getImage_num(); i++) {
            uriComponents.queryParam("image_type%5B%5D", imageCodes.get(i));
        }
        for (int i = 0; i < ekycRequestDto.getImage_num(); i++) {
            uriComponents.queryParam("movie%5B%5D", imageCodes.get(i));
        }
        uriComponents.queryParam("face_judge", imageCodes.get(0));
        System.out.println(uriComponents.hashCode());
        uriComponents.queryParam("hash", ekycRequestDto.getHash());
        return uriComponents;
    }

    @Override
    public AuthResponseDto performAuth(EkycRequestDto authRequest) throws UnsupportedEncodingException {
        AuthResponseDto authResponseDto = new AuthResponseDto();
        List<NameValuePair> formData = new ArrayList<>();
        formData.add(new BasicNameValuePair("client_code", authRequest.getClient_code()));
        formData.add(new BasicNameValuePair("route_key", authRequest.getRoute_key()));
        formData.add(new BasicNameValuePair("api_num", String.valueOf(authRequest.getApi_num())));
        formData.add(new BasicNameValuePair("image_num", String.valueOf(authRequest.getImage_num())));
        formData.add(new BasicNameValuePair("pageUrl", "https://dev-ap-dtrust.double-std.com/service/api/auth"));
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(phpAuthApi);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Host", "dev-ap-dtrust.double-std.com");
            httpPost.setHeader("Accept", "application/x-www-form-urlencoded; charset=UTF-16");
            httpPost.setHeader("Authorization", "Basic ZHRydXN0dXNlcjpkdHJ1c3QwNzAz");
            httpPost.setEntity(new UrlEncodedFormEntity(formData, StandardCharsets.UTF_8));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = rd.readLine())) {
                content.append(line);
            }
            System.out.println(content);
            authResponseDto = objectMapper.readValue(content.toString(), AuthResponseDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authResponseDto;
    }

    @Override
    public GetDataResponse getDataOfUser(EkycRequestDto ekycRequestDto) {
        GetDataResponse getDataResponse = new GetDataResponse();
        List<NameValuePair> formData = new ArrayList<>();
        formData.add(new BasicNameValuePair("client_code", ekycRequestDto.getClient_code()));
        formData.add(new BasicNameValuePair("route_key", ekycRequestDto.getRoute_key()));
        formData.add(new BasicNameValuePair("id", String.valueOf(ekycRequestDto.getId())));
        formData.add(new BasicNameValuePair("pageUrl", "https://dev-ap-dtrust.double-std.com/service/api/get_data"));
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(phpGetDataApi);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Host", "dev-ap-dtrust.double-std.com");
            httpPost.setHeader("Accept", "application/x-www-form-urlencoded; charset=UTF-16");
            httpPost.setEntity(new UrlEncodedFormEntity(formData, StandardCharsets.UTF_8));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = rd.readLine())) {
                content.append(line);
            }
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            getDataResponse = objectMapper.readValue(content.toString(), GetDataResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getDataResponse;
    }

    @Override
    public PhotoStatusResponse getPhotoStatus(EkycRequestDto ekycRequestDto) {
        PhotoStatusResponse photoStatusResponse = new PhotoStatusResponse();
        List<NameValuePair> formData = new ArrayList<>();
        formData.add(new BasicNameValuePair("client_code", ekycRequestDto.getClient_code()));
        formData.add(new BasicNameValuePair("route_key", ekycRequestDto.getRoute_key()));
        formData.add(new BasicNameValuePair("id", String.valueOf(ekycRequestDto.getId())));
        formData.add(new BasicNameValuePair("hash", ekycRequestDto.getHash()));
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(phpAuthApi);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Host", "dev-ap-dtrust.double-std.com");
            httpPost.setHeader("Accept", "application/x-www-form-urlencoded; charset=UTF-16");
//            httpPost.setHeader("Authorization", "Basic ZHRydXN0dXNlcjpkdHJ1c3QwNzAz");
            httpPost.setEntity(new UrlEncodedFormEntity(formData, StandardCharsets.UTF_8));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = rd.readLine())) {
                content.append(line);
            }
            photoStatusResponse = objectMapper.readValue(content.toString(), PhotoStatusResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return photoStatusResponse;
    }


    @Override
    public Resource getImageData(EkycRequestDto ekycRequestDto) {
        List<NameValuePair> formData = new ArrayList<>();
        formData.add(new BasicNameValuePair("client_code", ekycRequestDto.getClient_code()));
        formData.add(new BasicNameValuePair("route_key", ekycRequestDto.getRoute_key()));
        formData.add(new BasicNameValuePair("id", String.valueOf(ekycRequestDto.getId())));
        formData.add(new BasicNameValuePair("masking", String.valueOf(ekycRequestDto.getMasking())));
        formData.add(new BasicNameValuePair("pageUrl", ekycRequestDto.getPageUrl()));
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(phpGetImageDataApi);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Host", "dev-ap-dtrust.double-std.com");
            httpPost.setHeader("Accept", "application/x-www-form-urlencoded; charset=UTF-16");
            httpPost.setEntity(new UrlEncodedFormEntity(formData, StandardCharsets.UTF_8));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            InputStream inputStream = response.getEntity().getContent();
            byte[] imageBytes = inputStream.readAllBytes();
            inputStream.close();
            Resource resource = new ByteArrayResource(imageBytes);
            return resource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public ResponseEntity<byte[]> getVideoData(EkycRequestDto ekycRequestDto) {
//        try {
//            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//            HttpPost httpPost = new HttpPost(phpGetVideoDataApi);
//            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//            // Set other headers as needed
//
//            List<NameValuePair> formData = new ArrayList<>();
//            // Add form data to the list
//
//            httpPost.setEntity(new UrlEncodedFormEntity(formData, StandardCharsets.UTF_8));
//
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            InputStream inputStream = response.getEntity().getContent();
//
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            byte[] buffer = new bu;
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                byteArrayOutputStream.write(buffer, 0, bytesRead);
//            }
//            byte[] videoBytes = byteArrayOutputStream.toByteArray();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            // Set other headers if needed
//
//            return new ResponseEntity<>(videoBytes, headers, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Override
    public Resource getVideoData(EkycRequestDto ekycRequestDto) {
        List<NameValuePair> formData = new ArrayList<>();
        formData.add(new BasicNameValuePair("client_code", ekycRequestDto.getClient_code()));
        formData.add(new BasicNameValuePair("route_key", ekycRequestDto.getRoute_key()));
        formData.add(new BasicNameValuePair("id", String.valueOf(ekycRequestDto.getId())));
        formData.add(new BasicNameValuePair("pageUrl", ekycRequestDto.getPageUrl()));
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(phpGetVideoDataApi);
            httpPost.setHeader("Accept", "application/octet-stream");
            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Host", "dev-ap-dtrust.double-std.com");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-16");
            httpPost.setEntity(new UrlEncodedFormEntity(formData, StandardCharsets.UTF_8));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            InputStream inputStream = response.getEntity().getContent();
            byte[] imageBytes = inputStream.readAllBytes();
            inputStream.close();
            Resource resource = new ByteArrayResource(imageBytes);
            return resource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        String requestBody = formData.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));
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


