package gr.ds.restapi.controller;

import gr.ds.restapi.entity.Admin;
import gr.ds.restapi.entity.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/root")
public class AdminHomeController {


    @GetMapping("/home")
    public String adminHome(){
        return "admin-pages/admin-home-page";
    }

    @GetMapping("/home/citizens")
    public String citizenHome(){
        return "admin-pages/admin-citizen-page";
    }

    @GetMapping("/home/vets")
    public String vetHome(){
        return "admin-pages/admin-vet-page";
    }

    @GetMapping("/home/civics")
    public String civicHome(){
        return "admin-pages/admin-civic-page";
    }

    @GetMapping("/home/users")
    public String userHome(){return "admin-pages/admin-user-page";}



}
