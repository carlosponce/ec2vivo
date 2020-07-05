package com.okta.spring.example.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.spring.example.xmldto.ResponseMsg;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StreamProviderService {

    public ResponseMsg getVideoList() {
        final String uri = "http://localhost:8080/api/streamvideoprovider/getListVideosJson";

        // RestTemplate restTemplate = new RestTemplate();

        /*
         * String result = restTemplate.getForObject(uri, String.class);
         * 
         * ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(uri,
         * Object[].class); Object[] objects = responseEntity.getBody(); MediaType
         * contentType = responseEntity.getHeaders().getContentType(); HttpStatus
         * statusCode = responseEntity.getStatusCode();
         * 
         * Object[] forNow = restTemplate.getForObject(uri, Object[].class);
         */

        /*
         * HttpHeaders headers = new HttpHeaders();
         * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         * headers.setContentType(MediaType.APPLICATION_JSON); HttpEntity <String>
         * entity = new HttpEntity<String>(headers);
         */

        // ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET,
        // entity, String.class);

        // Gson gson = new Gson(); //instancie o obj Gson
        // String jsonInString = response.getBody(); //se não for uma String, o que acho
        // difícil, converta

        // System.out.println(jsonInString);

        /*
         * Type listType = new TypeToken<ArrayList<VideoList>>(){}.getType();
         * List<VideoList> videoList = gson.fromJson(jsonInString, listType);
         */

        // VideoList videoList = gson.fromJson(uri, VideoList.class);

        // System.out.println("Video: " + videoList.getResult());

        RestTemplate restTemplate = new RestTemplate();
        // VideoList response = restTemplate.getForObject(uri, VideoList.class);
        // VideoList[] response = restTemplate.getForObject(uri, VideoList[].class);
        // Video[] response = restTemplate.getForObject(uri, Video[].class);

        String stringJsonResponse = restTemplate.getForObject(uri, String.class);

        System.out.println(stringJsonResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMsg videoList;

        try {
            videoList = objectMapper.readValue(stringJsonResponse, ResponseMsg.class);
        } catch (JsonProcessingException e) {
            videoList = new ResponseMsg();
            e.printStackTrace();
        }

        return videoList;

    }

}