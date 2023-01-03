package gr.ds.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import gr.ds.restapi.dao.EntityDAO;
import gr.ds.restapi.entity.Citizen;
import gr.ds.restapi.entity.Pet;
import gr.ds.restapi.services.CustomUserDetails;
import gr.ds.restapi.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/citizen")
public class CitizenController {

    //Authentication auth = SecurityContextHolder.getContext().getAuthentication();


    @Autowired
    EntityDAO<Citizen> citizenDAO;

    /*@Autowired
    IPetService petService;*/

    @Autowired
    CustomUserDetails userDetails;

    @Autowired
    PetService petService;

    @GetMapping("/home")
    public String CitizenInfo() throws JsonProcessingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String username = auth.getName();


        System.out.println("Citizen has the following privileges: " + userDetails.loadUserByUsername(username).getAuthorities());

        System.out.println(auth.getAuthorities());
        Citizen citizen = citizenDAO.getEntity(username);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(citizen);


        return json;
    }

    @PostMapping("/add-pet") //TODO fix empty json request
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addPet(@RequestBody Pet pet){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String username = auth.getName();

        Citizen citizen = citizenDAO.getEntity(username);
        pet.setCitizen(citizen);
        pet.setOwnerCode(citizen.getCode());
        citizen.addPet(pet);
        citizenDAO.updateEntity(citizen);
        return new ResponseEntity<>(pet.getSerialNumber(), HttpStatus.CREATED);
    }

    @GetMapping("/pets")
    public String Pets() throws JsonProcessingException {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String username = auth.getName();

        List<Pet> pets = petService.getPetsByCitizenName(username);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pets);


        return json;

    }

    /*@GetMapping("/pending-pets")
    public String pendingPets(){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        List<Pet> pets = petService.getPendingPetsByUserName(username);

        String json = new Gson().toJson(pets);

        return json;

    }*/




}
