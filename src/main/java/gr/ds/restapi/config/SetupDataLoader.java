package gr.ds.restapi.config;

import gr.ds.restapi.dao.EntityDAO;
import gr.ds.restapi.entity.Admin;
import gr.ds.restapi.entity.Privilege;
import gr.ds.restapi.entity.Role;
import gr.ds.restapi.services.AdminService;
import org.hibernate.engine.spi.EntityUniqueKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean isSetup = false;

    @Autowired
    EntityDAO<Admin> adminDAO;
    @Autowired
    EntityDAO<Role> roleDAO;
    @Autowired
    EntityDAO<Privilege> privilegeDAO;

    @Autowired
    AdminService adminService;

    public void createAdminIfNotExists(Role role){

        if(adminDAO.getEntity("root0") != null) {
            System.out.println("admin already exists");
            return;
        }
        System.out.println("No administrator was found on the system.\nCreating one for you with the following credentials: username:root0, password:root0");

        Admin admin = new Admin(1, "root0", new BCryptPasswordEncoder().encode("root0"),"admin", "localhost",true);
        admin.addRole(role);
        adminDAO.addEntity(admin);


    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(isSetup)
            return;


        Privilege addUserPrivilege = createPrivilegeIfNotFound("ADD_USER");
        Privilege updateUserPrivilege = createPrivilegeIfNotFound("UPDATE_USER");
        Privilege deleteUserPrivilege = createPrivilegeIfNotFound("DELETE_USER");
        Privilege showUserPrivilege = createPrivilegeIfNotFound("VIEW_USER");
        Privilege showUsersPrivilege = createPrivilegeIfNotFound("VIEW_USERS");


        Privilege addPetPrivilege = createPrivilegeIfNotFound("ADD_PET");
        Privilege viewPetsPrivilege = createPrivilegeIfNotFound("VIEW_PETS");

        Privilege updatePetHistoryPrivilege = createPrivilegeIfNotFound("UPDATE_PET_HISTORY");
        Privilege verifyPetPrivilege = createPrivilegeIfNotFound("VERIFY_PET");

        List<Privilege> adminPrivileges = Arrays.asList(addUserPrivilege, updateUserPrivilege, deleteUserPrivilege, showUserPrivilege, showUsersPrivilege);
        List<Privilege> citizenPrivileges = Arrays.asList(addPetPrivilege, viewPetsPrivilege);
        List<Privilege> vetPrivileges = Arrays.asList(viewPetsPrivilege, updatePetHistoryPrivilege, verifyPetPrivilege);
        List<Privilege> civicPrivileges = List.of(viewPetsPrivilege);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_CITIZEN", citizenPrivileges);
        createRoleIfNotFound("ROLE_VET", vetPrivileges);
        createRoleIfNotFound("ROLE_CIVIC", civicPrivileges);


        Role adminRole = roleDAO.getEntity("ROLE_ADMIN");
        createAdminIfNotExists(adminRole);

        isSetup=true;

    }


    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeDAO.getEntity(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeDAO.addEntity(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, List<Privilege> privileges) {

        Role role = roleDAO.getEntity(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleDAO.addEntity(role);
        }
        return role;
    }
}
