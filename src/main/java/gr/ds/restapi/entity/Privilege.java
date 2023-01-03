package gr.ds.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;


    @ManyToMany(mappedBy = "privileges")
    @JsonIgnoreProperties("privilege")
    private List<Role> roles;


    public Privilege(){}

    public Privilege(String name, List<Role> roles){
        this.name = name;
        this.roles = roles;
    }

    public Privilege(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRole() {
        return roles;
    }

    public void setRoles(Role role) {
        this.roles = roles;
    }
}
