package com.ec.integration.streamingvideoprovider.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import com.ec.integration.streamingvideoprovider.StreamVideoProviderConfig;
import com.ec.integration.streamingvideoprovider.message.dto.RequestMsgDto;
import com.ec.integration.streamingvideoprovider.message.dto.ResponseMsgDto;
import com.ec.integration.streamingvideoprovider.message.xmldto.GroupPayload;
import com.ec.integration.streamingvideoprovider.message.xmldto.PasswordPayload;
import com.ec.integration.streamingvideoprovider.message.xmldto.ResponseMsg;
import com.ec.integration.streamingvideoprovider.message.xmldto.TicketPayload;
import com.ec.integration.streamingvideoprovider.message.xmldto.VideoPasswordPayload;
import com.ec.integration.streamingvideoprovider.service.StreamVideoProviderService;
import com.ec.vivo.domain.UserBilling;
import com.ec.vivo.domain.UserEc2Vivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/streamvideoprovider")
@CrossOrigin(origins = "*")
public class StreamVideoProviderController {

    @Autowired
    StreamVideoProviderConfig config;
    
    @Autowired
    StreamVideoProviderService service;

    @GetMapping(value = "/getToken")
    public String getToken() {
        return config.getToken();
    }

    @GetMapping(value = "/getPpvPackages")
    public String getPpvPackages() {
        return config.getPpvPackages();
    }

    @GetMapping(value = "/getListVideos")
    public String getListVideos() {
        return config.getListVideos();
    }

    @GetMapping(value = "/getListVideosJson")
    public ResponseMsg getListVideosJson() {
        return config.getListVideosJson();
    }

    @GetMapping(value = "/getListVideosJsonVideoSource")
    public ResponseMsg getListVideosJsonVideoSource(String videoSource) {
        return config.getListVideosJson(videoSource);
    }
    

    @GetMapping(value = "/getVideobyRefNo")
    public ResponseMsg getVideobyRefNo(String refNo) {
        return config.getVideobyRefNo(refNo);
    }

    @GetMapping(value = "/getPrimaryVideoImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]>  getPrimaryVideoImage(String videoRef, String imageType) {
        try {
            return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_JPEG).body(config.getImageWithMediaType(videoRef, imageType));
        } catch (IOException e) {
            System.out.println("### NO se pudo obtener la imagen!");
            e.printStackTrace();
            return ResponseEntity.badRequest().contentType(MediaType.IMAGE_JPEG).body(null);
        }
    }

    @GetMapping(value = "/getPrimaryVideoImageBase64")
    public ResponseEntity<String>  getPrimaryVideoImageBase64(String videoRef, String imageType) {
        try {

            String jsonbas64StringVideo = java.util.Base64.getMimeEncoder().encodeToString(config.getImageWithMediaType(videoRef, imageType));

            return ResponseEntity
                .ok().body(jsonbas64StringVideo);

        } catch (IOException e) {
            System.out.println("### NO se pudo obtener la imagen!");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "/getVideoImages", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseMsg getVideoImages(String imageType) {

        return config.getListVideosImageJson(imageType);

    }

    @PostMapping("/createPasswordPackage")
    public ResponseEntity<String> createPasswordPackage(@RequestBody GroupPayload groupinfo) throws URISyntaxException {
        System.out.println("inside createPasswordPackage controller");
        String str = config.createPasswordPackage(groupinfo);
        return new ResponseEntity<String>(str, HttpStatus.OK);
    }

    @PostMapping("/createPasswordPackageJson")
    public ResponseEntity<ResponseMsg> createPasswordPackageJson(@RequestBody GroupPayload groupinfo)
            throws URISyntaxException {
        System.out.println("inside createPasswordPackageJson controller");
        ResponseMsg response = config.createPasswordPackageJson(groupinfo);
        return new ResponseEntity<ResponseMsg>(response, HttpStatus.OK);
    }

    @PostMapping("/createPpvPackage")
    public ResponseEntity<String> createPpvPackage(@RequestBody TicketPayload obj) throws URISyntaxException {
        System.out.println("inside createPpvPackage controller");
        String str = config.createPpvPackage(obj);
        return new ResponseEntity<String>(str, HttpStatus.OK);
    }

    @PostMapping("/createPpvPackageJson")
    public ResponseEntity<ResponseMsg> createPpvdPackageJson(@RequestBody TicketPayload obj) throws URISyntaxException {
        System.out.println("inside createPpvPackageJspn controller");
        ResponseMsg response = config.createPpvPackageJson(obj);
        return new ResponseEntity<ResponseMsg>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getListPasswordPackages")
    public String getListPasswordPackages() {
        return config.getListPasswordPackages();
    }

    @GetMapping(value = "/getListPasswordPackagesJson")
    public ResponseMsg getListPasswordPackagesJson() {
        return config.getListPasswordPackagesJson();
    }

    @PostMapping("/setVideoPassword")
    public ResponseEntity<String> setVideoPassword(@RequestBody VideoPasswordPayload obj) throws URISyntaxException {
        System.out.println("inside setVideoPassword controller");
        String str = config.setVideoPassword(obj);
        return new ResponseEntity<String>(str, HttpStatus.OK);
    }

    @PostMapping("/setVideoPasswordJson")
    public ResponseEntity<ResponseMsg> setVideoPasswordJson(@RequestBody VideoPasswordPayload obj)
            throws URISyntaxException {
        System.out.println("inside setVideoPasswordJson controller");
        ResponseMsg response = config.setVideoPasswordJson(obj);
        return new ResponseEntity<ResponseMsg>(response, HttpStatus.OK);
    }

    @PostMapping("/addGroupPassword")
    public ResponseEntity<String> addGroupPassword(@RequestBody PasswordPayload obj) throws URISyntaxException {
        System.out.println("inside addGroupPassword controller");
        String str = config.addGroupPassword(obj);
        return new ResponseEntity<String>(str, HttpStatus.OK);
    }

    @PostMapping("/addGroupPasswordJson")
    public ResponseEntity<ResponseMsg> addGroupPasswordJson(@RequestBody PasswordPayload obj)
            throws URISyntaxException {
        System.out.println("inside addGroupPasswordJson controller");
        ResponseMsg response = config.addGroupPasswordJson(obj);
        return new ResponseEntity<ResponseMsg>(response, HttpStatus.OK);
    }
    
    @PostMapping("/saveUser")
    public ResponseEntity<ResponseMsgDto> saveUser(@RequestBody UserEc2Vivo obj)
            throws URISyntaxException {
        System.out.println("inside saveUser controller");
        ResponseMsgDto response = service.saveUser(obj);
        return new ResponseEntity<ResponseMsgDto>(response, HttpStatus.OK);
    }
    
    @PostMapping("/saveBilling")
    public ResponseEntity<ResponseMsgDto> saveBilling(@RequestBody UserBilling obj)
            throws URISyntaxException {
        System.out.println("inside saveBilling controller");
        ResponseMsgDto response = service.saveBilling(obj);
        return new ResponseEntity<ResponseMsgDto>(response, HttpStatus.OK);
    }
    
    @PostMapping("/getBillings")
    public ResponseEntity<ResponseMsgDto> getBillings(@RequestBody RequestMsgDto obj)
            throws URISyntaxException {
        System.out.println("inside saveBilling controller");
        ResponseMsgDto response = service.getBillings(obj.getEmail());
        return new ResponseEntity<ResponseMsgDto>(response, HttpStatus.OK);
    }

}