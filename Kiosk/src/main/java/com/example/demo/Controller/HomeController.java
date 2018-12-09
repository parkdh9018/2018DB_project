package com.example.demo.Controller;

import com.example.demo.DB.DBconnect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }
    @GetMapping("/evaluation")
    public String evaluation(Model model){
        return "evaluation";
    }
    @GetMapping("/payment")
    public String payment(Model model){
        return "payment";
    }
    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }
    @GetMapping("/support")
    public String support(Model model){
        return "support";
    }

}
