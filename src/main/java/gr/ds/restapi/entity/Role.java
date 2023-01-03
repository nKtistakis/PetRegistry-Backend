package gr.ds.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    private String name;

    /*@Column(name = "user_id", insertable = false, updatable = false)
    private int userId;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties
    private List<User> users;*/

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("role")
    List<Privilege> privileges;


   /* public void removeUser(){
        this.users = null;
    }*/

    public Role() { }


    public Role(String name) {
        this.name = name;
    }

    public Role(int id, String name){
        this.id=id;
        this.name=name;
    }
   /* public Role(String name,List<User> users){
        this.id=id;
        this.name=name;
        this.users = users;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<User> getUser() {
        return users;
    }
*/
   /* public void setUsers(List<User> users) {
        this.users = users;
    }*/

    @Override
    public String toString() {
        return this.name;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
    /*public void addUser(User user){
        users.add(user);
    }*/
}
