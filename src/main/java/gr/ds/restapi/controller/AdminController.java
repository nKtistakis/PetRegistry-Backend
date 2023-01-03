package gr.ds.restapi.controller;


import gr.ds.restapi.dao.EntityDAO;
import gr.ds.restapi.entity.*;
import gr.ds.restapi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/root/users")
public class AdminController {


    @Autowired
    EntityDAO<Citizen> citizenDAO;

    @Autowired
    EntityDAO<Vet> vetDAO;

    @Autowired
    EntityDAO<CivicOfficial> civicDAO;

    @Autowired
    EntityDAO<Role> roleDAO;

    @GetMapping("/all")
    public String getUsers(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "list-users";
    }

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username, Model model){
        User user = userService.getUserByUsername(username);
        if (user == null){
            model.addAttribute("error", "User with username: " + username + " not found");
            return "errors-page";
        }
        System.out.println(user.getId() + "\t" + user.getFullName());
        model.addAttribute("user", user);

        return "user-info";
    }

    @GetMapping("/{usertype}/all") //TODO
    public String getUsersByType(@PathVariable String usertype, Model model){


        switch (usertype){
            case "citizens":
                List<Citizen> citizens = citizenDAO.showALl();
                model.addAttribute("citizens", citizens);
                return "list-citizens";

            case "vets":
                List<Vet> vets = vetDAO.showALl();
                model.addAttribute("vets", vets);
                return "list-vets";

            case "civics":
                List<CivicOfficial> civics = civicDAO.showALl();
                model.addAttribute("civics", civics);
                return "list-civics";
            default:
                break;
        }
            return "";

    }

    //User section

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    //DELETE USER
    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username){
        User user = userService.findByUsername(username);
        //roleService.deleteByUserId(user.getId());
        userService.deleteUserByUsername(username);
        return "success-form";
    }

    //UPDATE USER
    @GetMapping("/update/{username}")
    public String updateUser(@PathVariable String username, Model model){

        User user = userService.getUserByUsername(username);
        System.out.println(user.getId());
        model.addAttribute("user", user);

        return "user-update-form";
    }

    @PostMapping("/update/{username}")
    public String updateUserSubmit(@ModelAttribute User user){ //TODO add roles to add citizen and update citizen

       User oldUser = userService.getById(user.getId());

       oldUser.setUsername(user.getUsername());
       System.out.println(user.getUsername());
       oldUser.setPasscode(user.getPasscode());
       oldUser.setFullName(user.getFullName());
       oldUser.setRegion(user.getRegion());
       oldUser.setEnabled(user.getEnabled());

       userService.save(oldUser);
        return "success-form";
    }

    //Find user

    @GetMapping("/find")
    public String findUser(){
        return "admin-pages/find-user-page";
    }

    //Citizen section

    @Autowired
    CitizenService citizenService;


    @GetMapping("/add-citizen")
    public String addCitizen(Model model){

        model.addAttribute("citizen", new Citizen());
        return "citizen-add-form";
    }

    @PostMapping("/add-citizen")
    public String addCitizenSubmit(@Valid @ModelAttribute Citizen citizen, BindingResult bindingResult){

        if(bindingResult.hasErrors())return "citizen-add-form";
        System.out.println("id: "+citizen.getCode());
        citizen.setPasscode(new BCryptPasswordEncoder().encode(citizen.getPasscode()));
        Role citizenRole = roleDAO.getEntity("ROLE_CITIZEN");
        citizen.addRole(citizenRole);
        citizenDAO.addEntity(citizen);

        return "success-form";
    }


    @GetMapping("/update-citizen/{code}")
    public String updateCitizen(@PathVariable int code, Model model){

        Citizen citizen = citizenService.getFullCitizenByCode(code);
        System.out.println(citizen.getId());
        model.addAttribute("citizen", citizen);
        return "citizen-update-form";
    }
    @PostMapping("/update-citizen/{code}")
    public String updateCitizenSubmit(@Valid @ModelAttribute Citizen citizen, BindingResult bindingResult){ //TODO add roles to add citizen and update citizen

        if(bindingResult.hasErrors())  return "citizen-update-form";

        Citizen dbCitizen = citizenService.getCitizenByCode(citizen.getCode());

        dbCitizen.setUsername(citizen.getUsername());
        dbCitizen.setPasscode(citizen.getPasscode());
        dbCitizen.setFullName(citizen.getFullName());
        dbCitizen.setRegion(citizen.getRegion());
        dbCitizen.setAddress(citizen.getAddress());
        dbCitizen.setPhoneNumber(citizen.getPhoneNumber());
        dbCitizen.setEmail(citizen.getEmail());
        dbCitizen.setEnabled(citizen.getEnabled());

        citizenService.save(dbCitizen);

        return "success-form";
    }


    //Vet section

    @Autowired
    VetService vetService;

    @GetMapping("/add-vet")
    public String addVet(Model model){
        model.addAttribute("vet", new Vet());
        return "vet-add-form";
    }

    @PostMapping("/add-vet")
    public String addVetSubmit(@Valid @ModelAttribute Vet vet, BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "vet-add-form";
        vet.setPasscode(new BCryptPasswordEncoder().encode(vet.getPasscode()));

        Role vetRole = roleDAO.getEntity("ROLE_VET");
        vet.addRole(vetRole);
        vetDAO.addEntity(vet);

        return "success-form";
    }

    @GetMapping("/update-vet/{code}")
    public String updateVet(@PathVariable int code, Model model){

        Vet vet = vetService.getVetByCode(code);
        System.out.println(vet.getId());
        model.addAttribute("vet", vet);
        return "vet-update-form";
    }

    @PostMapping("/update-vet/{code}")
    public String updateVetSubmit(@Valid @ModelAttribute Vet vet, BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "vet-update-form";

        Vet dbVet = vetService.getVetByCode(vet.getCode());

        dbVet.setUsername(vet.getUsername());
        dbVet.setPasscode(vet.getPasscode());
        dbVet.setFullName(vet.getFullName());
        dbVet.setRegion(vet.getRegion());
        dbVet.setName(vet.getName());
        dbVet.setEnabled(vet.getEnabled());

        vetService.save(dbVet);

        return "success-form";
    }

    //Civic section

    @Autowired
    CivicService civicService;

    @GetMapping("/add-civic")
    public String addCivic(Model model){
        model.addAttribute("civic", new CivicOfficial());
        return "civic-add-form";
    }

    @PostMapping("/add-civic")
    public String addCivicSubmit(@Valid @ModelAttribute CivicOfficial civic, BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "civic-add-form";
        civic.setPasscode(new BCryptPasswordEncoder().encode(civic.getPasscode()));

        Role civicRole = roleDAO.getEntity("ROLE_CIVIC");
        civic.addRole(civicRole);
        civicDAO.addEntity(civic);

        return "success-form";
    }

    @GetMapping("/update-civic/{code}")
    public String updateCivic(@PathVariable int code, Model model){

        CivicOfficial civic = civicService.getCivicOfficialByCode(code);
        System.out.println(civic.getId());
        model.addAttribute("civic", civic);
        return "civic-update-form";
    }

    @PostMapping("/update-civic/{code}")
    public String updateCivicSubmit(@Valid @ModelAttribute CivicOfficial civic, BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "civic-update-form";
        CivicOfficial dbCivic = civicService.getCivicOfficialByCode(civic.getCode());

        dbCivic.setUsername(civic.getUsername());
        dbCivic.setPasscode(civic.getPasscode());
        dbCivic.setFullName(civic.getFullName());
        dbCivic.setRegion(civic.getRegion());
        dbCivic.setEnabled(civic.getEnabled());

        civicService.save(dbCivic);

        return "success-form";
    }

    //Admin section

    @Autowired
    EntityDAO<Admin> adminDAO;

    @Autowired
    AdminService adminService;

    //TODO add all user info on update user
    @GetMapping("/update-root/{id}")
    public String updateRoot(@PathVariable int id, Model model){

        Admin admin = adminService.getById(id);
        System.out.println(admin.getId());
        model.addAttribute("root", admin);
        return "root-update-form";
    }

    @PostMapping("/update-root/{id}")
    public String updateRootSubmit(@Valid @ModelAttribute Admin admin, BindingResult bindingResult){

        if(bindingResult.hasErrors())  return "root-update-form";
        Admin dbAdmin = adminService.getById(admin.getId());
        dbAdmin.setUsername(admin.getUsername());
        dbAdmin.setPasscode(admin.getPasscode());
        dbAdmin.setFullName(admin.getFullName());
        dbAdmin.setRegion(admin.getRegion());
        dbAdmin.setEnabled(admin.getEnabled());

        adminService.save(dbAdmin);

        return "success-form";
    }



}

