package gr.ds.restapi.controller;

import gr.ds.restapi.dao.EntityDAO;
import gr.ds.restapi.entity.Citizen;
import gr.ds.restapi.entity.Role;
import gr.ds.restapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
@RequestMapping("/api/users/citizens")
public class UserController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private EntityDAO<Citizen> citizenDAO;



    @RequestMapping("/all")
    public String listCitizens(Model model){

        List<Citizen> citizens = citizenDAO.showALl();

        model.addAttribute("citizens", citizens);

        return "list-citizens";

    }



}
