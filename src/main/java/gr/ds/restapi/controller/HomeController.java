package gr.ds.restapi.controller;

import gr.ds.restapi.services.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String root(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        System.out.println(auth.getAuthorities());
        if(username.equals("anonymousUser")) return "redirect:/login";
        for(GrantedAuthority g : auth.getAuthorities()){
            if(g.getAuthority().equals("ROLE_ADMIN")) return "redirect:/api/root/home";
        }

        return "home-page";
    }

}
