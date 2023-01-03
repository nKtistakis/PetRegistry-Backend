package gr.ds.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootWelcomeController {

    @GetMapping("/welcome-root")
    public String welcome(){ return "Welcome Admin!!"; }
}
