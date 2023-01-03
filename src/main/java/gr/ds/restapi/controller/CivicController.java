package gr.ds.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.ds.restapi.dao.PetRepository;
import gr.ds.restapi.dao.EntityDAO;
import gr.ds.restapi.entity.CivicOfficial;
import gr.ds.restapi.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/civic")
public class CivicController {

    @Autowired
    EntityDAO<CivicOfficial> civicDAO;

    @Autowired
    PetRepository petRepository;

    @GetMapping("/home")
    public String info() throws JsonProcessingException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String username = auth.getName();

        CivicOfficial civic = civicDAO.getEntity(username);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(civic);

        return json;
    }

    @GetMapping("/show-pets")
    public String showPets() throws JsonProcessingException {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String username = auth.getName();

        CivicOfficial civic = civicDAO.getEntity(username);

        List<Pet> pets = petRepository.getPetsByRegion(civic.getRegion());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pets);
        System.out.println(json);
        return json;

    }
}
