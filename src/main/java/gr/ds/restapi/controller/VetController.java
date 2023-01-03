package gr.ds.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.ds.restapi.dao.EntityDAO;
import gr.ds.restapi.helper.HelperClasses;
import gr.ds.restapi.entity.MedicalHistory;
import gr.ds.restapi.entity.Pet;
import gr.ds.restapi.entity.Vet;
import gr.ds.restapi.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vet")
public class VetController {

    @Autowired
    EntityDAO<Vet> vetDAO;

    @Autowired
    EntityDAO<Pet> petDAO;

    @Autowired
    PetService petService;

    @GetMapping("/home")
    public String info() throws JsonProcessingException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String username = auth.getName();

        Vet vet = vetDAO.getEntity(username);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(vet);

        return json;
    }

    @PostMapping("/show-pending")
    public String showPending(@RequestBody HelperClasses.JSONString citizenNameJsonString) throws JsonProcessingException {

        String citizenName = citizenNameJsonString.getString();
        List<Pet> pendingPets = petService.getPendingPetsByCitizenName(citizenName);
        System.out.println(pendingPets.size());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pendingPets);
        System.out.println(json);

        return json;
    }

    @PostMapping("/verify-pet")
    public String verifyPet(@RequestBody HelperClasses.JSONString petNumberJsonString) throws JsonProcessingException {


        int serialNum = Integer.parseInt(petNumberJsonString.getString());

        Pet pet = petService.getById(serialNum);
        System.out.println("pet pending: " + pet.getIs_approved());
        pet.setIs_approved(1);
        System.out.println("pet pending: " + pet.getIs_approved());
        petService.verifyPet(pet.getSerialNumber());

        Pet petResponse = new Pet(pet.getSerialNumber(), pet.getOwnerCode(), pet.getType(), pet.getRace(), pet.getSex(), pet.getBirthDate(), pet.getIs_approved());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(petResponse);

        return json;
    }


    @PostMapping("/update-med-history")
    public String updatePetHistory(@RequestBody MedicalHistory medOp){

        Pet pet = petService.getById(medOp.getPetNumber());

        if(pet.getIs_approved() == 0){
            return "Pet with serial number: " + pet.getSerialNumber()+ " is not verified.\nPlease verify it first and then update its medical history";
        }
        medOp.setPet(pet);

        pet.addMedicalOp(medOp);

        petDAO.updateEntity(pet);

        return medOp.getOperation();

    }

    @PostMapping("/get-pet")
    public String getPet(@RequestBody HelperClasses.JSONString petNumberJSONString) throws JsonProcessingException {

        int petNumber = Integer.parseInt(petNumberJSONString.getString());
        Pet pet = petService.getById(petNumber);
        System.out.println("Found pet: " + pet.getSerialNumber());

        Pet petResponse = new Pet(pet.getSerialNumber(), pet.getType(), pet.getRace(), pet.getSex(), pet.getBirthDate(), pet.getIs_approved());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(petResponse);
        return json;

    }


}
