package ec.com.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/index")
    public String index() {
        return "index";
    }
	
	@RequestMapping("/dashboardHome")
    public String dashboardHome() {
        return "dashboard-home";
    }
	
	@RequestMapping("/dashboardAccountPayment")
    public String dashboardAccountPayment() {
        return "dashboard-account-payment";
    }
	
	@RequestMapping("/dashboardAccount")
    public String dashboardAccount() {
        return "dashboard-account";
    }
	
	@RequestMapping("/dashboardComingSoon")
    public String dashboardComingSoon() {
        return "dashboard-coming-soon";
    }
	
	@RequestMapping("/dashboardFavorites")
    public String dashboardFavorites() {
        return "dashboard-favorites";
    }
	
	@RequestMapping("/dashboardMovieProfile")
    public String dashboardMovieProfile() {
        return "dashboard-movie-profile";
    }
	
	@RequestMapping("/dashboardMovies")
    public String dashboardMovies() {
        return "dashboard-movies";
    }
	
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
        return "landing.html";
    }
	
	@RequestMapping("/faqs")
    public String faqs() {
        return "faqs.html";
    }
	
	@RequestMapping("/modalLoginSimple")
    public String modalLoginSimple() {
        return "modal-login-simple";
    }
	
	@RequestMapping("/signupStep2")
    public String signupStep2() {
        return "signup-step2.html";
    }
	
	@RequestMapping("/signupStep3")
    public String signupStep3() {
        return "signup-step3.html";
    }
	
	@RequestMapping("/signupStep4")
    public String signupStep4() {
        return "signup-step4.html";
    }
	
	@RequestMapping("/signupWelcome")
    public String signupWelcome() {
        return "signup-welcome.html";
    }
	
	
}