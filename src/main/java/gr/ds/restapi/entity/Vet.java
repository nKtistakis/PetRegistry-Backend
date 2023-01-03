package gr.ds.restapi.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Vet  extends User implements Serializable {

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name="code")
    private int code;

    public Vet(){}

    public Vet(String name, int code) {

        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
