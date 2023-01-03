package gr.ds.restapi.controller;

import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(){ return "Welcome!!"; }

    @GetMapping("/welcome/error")
    public String error(){ return "Access Denied!!";}

    @GetMapping("/logged_in")
    public String loggedIn(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String authJson = new Gson().toJson(authentication);

        System.out.println(authJson);
        return authJson;
    }
}
