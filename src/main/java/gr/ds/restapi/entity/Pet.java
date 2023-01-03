package gr.ds.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity

public class Pet implements Serializable {

    @NotNull
    @Id
    @Column(name = "serial_number")
    private int serialNumber;

    @NotNull
    @Column(name = "owner_code",insertable=false, updatable=false)
    private int ownerCode;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name ="owner_code", referencedColumnName = "id")
    @JsonIgnoreProperties("pets")
    private Citizen citizen;

    @NotBlank
    @Column(name = "type")
    private String type;

    @NotBlank
    @Column(name = "race")
    private String race;

    @NotBlank
    @Column(name = "sex")
    private String sex;

    @NotBlank
    @Column(name = "birth_date")
    private String birthDate;

    @NotNull
    @Column(name = "is_approved")
    private int is_approved;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnoreProperties("pet")
    private List<MedicalHistory> medicalOps = new ArrayList<>();

    protected Pet(){}



    public Pet(int serialNumber, Citizen citizen, String type, String race, String sex, String birthDate, int is_approved) {
        this.serialNumber = serialNumber;
        this.citizen = citizen;
        this.ownerCode = citizen.getId();
        this.type = type;
        this.race = race;
        this.sex = sex;
        this.birthDate = birthDate;
        this.is_approved = is_approved;
    }

    public Pet(int serialNumber, int ownerCode, String type, String race, String sex, String birthDate, int is_approved) {
        this.serialNumber = serialNumber;
        this.ownerCode = ownerCode;
        this.type = type;
        this.race = race;
        this.sex = sex;
        this.birthDate = birthDate;
        this.is_approved = is_approved;
    }

    public Pet(int serialNumber, String birthDate, String race, String sex, String type, int is_approved){
        this.serialNumber = serialNumber;
        this.birthDate = birthDate;
        this.race = race;
        this.sex = sex;
        this.type = type;
        this.is_approved = is_approved;
    }

    public Pet(int serialNumber, String birthDate, String race, String sex, String type){
        this.serialNumber = serialNumber;
        this.birthDate = birthDate;
        this.race = race;
        this.sex = sex;
        this.type = type;
        is_approved = 0;
    }

    public Pet(int serialNumber, String birthDate, String race, String sex, String type, int is_approved, Citizen citizen){
        this.serialNumber = serialNumber;
        this.birthDate = birthDate;
        this.race = race;
        this.sex = sex;
        this.type = type;
        this.is_approved = is_approved;
        this.citizen = citizen;
    }

    public Pet(int serialNumber, String birthDate, String race, String sex, String type, int is_approved, int ownerCode){
        this.serialNumber = serialNumber;
        this.birthDate = birthDate;
        this.race = race;
        this.sex = sex;
        this.type = type;
        this.is_approved = is_approved;
        this.ownerCode = ownerCode;
    }

    public void addMedicalOp(MedicalHistory operation){
        medicalOps.add(operation);
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(int ownerCode) {
        this.ownerCode = ownerCode;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(int is_approved) {
        this.is_approved = is_approved;
    }

    public void deleteMedOps(){
        this.medicalOps.clear();
    }

}
