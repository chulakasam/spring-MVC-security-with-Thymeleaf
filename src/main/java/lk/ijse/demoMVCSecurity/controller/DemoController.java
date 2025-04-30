package lk.ijse.demoMVCSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome(){
        return "home";
    }
    @GetMapping("/leaders")
    public String leaders(){
        return "leaderForm";
    }
    @GetMapping("/systems")
    public String systems(){
        return "systemForm";
    }
}
