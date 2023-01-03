package gr.ds.restapi.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User implements Serializable {

    public Admin(){}

    public Admin(int id, String username, String passcode, String fullName, String region, boolean enabled) {
        setId(id);
        setUsername(username);
        setPasscode(passcode);
        setFullName(fullName);
        setRegion(region);
        setEnabled(enabled);
    }
}
