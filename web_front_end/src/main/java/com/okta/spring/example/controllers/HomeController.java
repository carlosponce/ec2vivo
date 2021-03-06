package com.okta.spring.example.controllers;

import java.util.List;
import java.util.stream.Stream;

import com.okta.spring.example.services.StreamProviderService;
import com.okta.spring.example.xmldto.ResponseMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import com.okta.spring.example.domain.UserEc2Vivo;

@Controller
public class HomeController {

    @Autowired
    private StreamProviderService streamProviderService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
/*
    @RequestMapping("/getVideoList")
    @ResponseBody
    public ResponseMsg  getVideoListJson() {
        return streamProviderService.getVideoList();
    }*/

    /*@RequestMapping("/home/getVideoList")
    public String getVideoList(Model model) {
        //model.addAttribute("videoList", streamProviderService.getVideoList());
        return "home";
    }*/

    /*@RequestMapping("/home/getVideoList/{clipKey}")
    public String getVideoListDetail(@PathVariable("clipKey") String clipKey, Model model) {
        model.addAttribute("clipKey", clipKey);
        return getVideoList(model);
    }*/
    

    @RequestMapping("/dashboardMovieProfile/{refNo}")
    public String dashboardMovieProfile(@PathVariable("refNo") String refNo, Model model) {
        ResponseMsg respMesg = streamProviderService.getVideobyRefNo(refNo);

        String videoImgBase64 = streamProviderService.getVideoImageBase64(refNo,"thumb");

        model.addAttribute("respMesg", respMesg);

        if(respMesg.getVideoList() != null && respMesg.getVideoList().size() > 0)   model.addAttribute("clipKey", respMesg.getVideoList().get(0).getClipKey());

        model.addAttribute("videoImgBase64Thumb", videoImgBase64);

        return "dashboard-movie-profile";
    }

    @RequestMapping("/dashboardMovieProfile")
    public String dashboardMovieProfile(Model model) {

        return "dashboard-movie-profile";
    }

    @GetMapping("/index")
    public String index_2() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/dashboardHome")
    public String dashboardHome(Model model) {
        model.addAttribute("videoList", streamProviderService.getAllVideoList());
        return "dashboard-home";
    }

    @PostMapping("/saveUser")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveUser(@RequestBody UserEc2Vivo obj) {
        System.out.println("##### Iniciando proceso de registro de usuario ");
        streamProviderService.saveNewUser(obj);

    }


    @RequestMapping("/dashboardLive")
    public String dashboardLive(Model model) {
        model.addAttribute("videoList", streamProviderService.getVideoList("live"));
        return "dashboard-live";
    }

    @RequestMapping("/dashboardStore")
    public String dashboardStore(Model model) {
        return "dashboard-store";
    }
/*
	@RequestMapping("/dashboardAccountPayment")
    public String dashboardAccountPayment() {
        return "dashboard-account-payment";
    }*/

	@RequestMapping("/dashboardAccount")
    public String dashboardAccount() {
        return "dashboard-account";
    }
/*
	@RequestMapping("/dashboardComingSoon")
    public String dashboardComingSoon() {
        return "dashboard-coming-soon";
    }*/
	/*
	@RequestMapping("/dashboardFavorites")
    public String dashboardFavorites() {
        return "dashboard-favorites";
    }*/
	
	/*
	@RequestMapping("/dashboardMovies")
    public String dashboardMovies() {
        return "dashboard-movies";
    }*/

	/*
	@RequestMapping("/dashboardNewArrivals")
    public String dashboardNewArrivals() {
        return "dashboard-new-arrivals";
    }
	
	@RequestMapping("/dashboardPlaylists")
    public String dashboardPlaylists() {
        return "dashboard-playlists";
    }
	
	@RequestMapping("/dashboardProfile")
    public String dashboardProfile() {
        return "dashboard-profile";
    }
	
	@RequestMapping("/signupStep1")
    public String signupStep1() {
        return "signup-step1.html";
    }
	
	
	@RequestMapping("/landing")
    public String landing() {
        return "landing";
    }*/
	
	@RequestMapping("/faqs")
    public String faqs() {
        return "faqs";
    }
	/*
	@RequestMapping("/modalLoginSimple")
    public String modalLoginSimple() {
        return "modal-login-simple";
    }
	
	@RequestMapping("/signupStep2")
    public String signupStep2() {
        return "signup-step2";
    }
	
	@RequestMapping("/signupStep3")
    public String signupStep3() {
        return "signup-step3";
    }
	
	@RequestMapping("/signupStep4")
    public String signupStep4() {
        return "signup-step4";
    }
	
	@RequestMapping("/signupWelcome")
    public String signupWelcome() {
        return "signup-welcome";
    }*/

}