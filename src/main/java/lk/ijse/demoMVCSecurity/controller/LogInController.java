package lk.ijse.demoMVCSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {
    @GetMapping("/showLogInForm")
    public String showLogInForm(){
       // return "LogInForm";
        return "fancy-login";
    }

    @GetMapping("/access_denied")
    public String accessDenied(){
        // return "LogInForm";
        return "access_denied";
    }
}
