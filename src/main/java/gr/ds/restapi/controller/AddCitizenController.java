package gr.ds.restapi.controller;

import gr.ds.restapi.dao.EntityDAO;
import gr.ds.restapi.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddCitizenController {

    @Autowired
    private EntityDAO<Citizen> citizenDAO;

    @Autowired
    private EntityDAO<Admin> adminDAO;

    @Autowired
    private EntityDAO<CivicOfficial> civicDAO;


    @GetMapping("/add-citizen")
    public String addCitizen(){


        return "done";
    }

    @GetMapping("/delete-citizen")
    public String deleteCitizen(){

        return "done";
    }

    /*@GetMapping("/update-citizen")
    public String updateCitizen(){

        Citizen citizen = new Citizen(2, "user2", "user$2", "User 2", "region2", 1, "address 2", 22222, "user2@gmail.com");



        return "done";
    }*/ //TODO remove url


    /*@GetMapping("/add-citizens")
    public String addCitizens(){

        Citizen citizen1 = new Citizen(250010, )
        citizen1.addRole(new Role("ROLE_CITIZEN"));
        citizenDAO.addEntity(citizen1);
        Citizen citizen2 = new Citizen(12, "user2", "user", "User 2", "region2", 1, "dwada", 9087987, "dadada");
        citizen2.addRole(new Role("ROLE_USR"));
        citizenDAO.addEntity(citizen2);
        Citizen citizen3 = new Citizen(13, "user3", "user", "User 3", "region3", 1, "dwada", 9087987, "dadada");
        citizen3.addRole(new Role("ROLE_USR"));
        citizenDAO.addEntity(citizen3);
        Citizen citizen4 = new Citizen(14, "user4", "user", "User 4", "region4", 1, "dwada", 9087987, "dadada");
        citizen4.addRole(new Role("ROLE_USR"));
        citizenDAO.addEntity(citizen4);
        return "done";

    }*/ //TODO remove url

    @GetMapping("/add-civics")
    public String addCivs(){

        CivicOfficial citizen1 = new CivicOfficial(33000, "user1", "user", "User 1", "Kallithea, Attica, Greece", true);
        citizen1.addRole(new Role("ROLE_CIVIC"));
        civicDAO.addEntity(citizen1);


        return "done";

    }

    @GetMapping("/add-root")
    public String addRoot(){
        Admin user = new Admin(0, "root", "$2a$12$n.RnsPqyWjde63fhkghVNOr3J1LZadWxm49Kskqcr2nlmuWOBxxDG", "", "", true);
        user.addRole(new Role("ROLE_ADMIN"));
        adminDAO.addEntity(user);

        return "admin added";
    }
}
