package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pet")
public class Pet implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private Long age;
    private String weight;
    private String color;
    private boolean softDelete;

    public Pet() {
    }

    public boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<com.codegym.model.CareDetailHasPetDetail> getCareDetailHasPetDetail() {
        return CareDetailHasPetDetail;
    }

    public void setCareDetailHasPetDetail(Set<com.codegym.model.CareDetailHasPetDetail> careDetailHasPetDetail) {
        CareDetailHasPetDetail = careDetailHasPetDetail;
    }

    @OneToMany(targetEntity = CareDetailHasPetDetail.class)
    private Set<CareDetailHasPetDetail> CareDetailHasPetDetail;

    @ManyToOne
    @JoinColumn(name = "petKind_id")
    private PetKind petKind;
    public PetKind getPetKind() {
        return petKind;
    }
    public void setPetKind(PetKind petKind) {
        this.petKind = petKind;
    }


    @OneToMany(targetEntity = PetDetailHasMyTeam.class)
    private Set<PetDetailHasMyTeam> PetDetailHasMyTeam;

    public Set<com.codegym.model.PetDetailHasMyTeam> getPetDetailHasMyTeam() {
        return PetDetailHasMyTeam;
    }

    public void setPetDetailHasMyTeam(Set<com.codegym.model.PetDetailHasMyTeam> petDetailHasMyTeam) {
        PetDetailHasMyTeam = petDetailHasMyTeam;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Pet.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Pet pet = (Pet) target;
        String petName = pet.getName();
        Long petAge = pet.getAge();
        String petWeight = pet.getWeight();
        String petColor = pet.getColor();
        ValidationUtils.rejectIfEmpty(errors, "name", "petName.empty");
        ValidationUtils.rejectIfEmpty(errors, "weight", "petWeight.empty");
        ValidationUtils.rejectIfEmpty(errors, "color", "petColor.empty");
        ValidationUtils.rejectIfEmpty(errors, "age", "petAge.empty");
        if (!petName.matches("^[a-zA-ZÀ-ỹ-\\s]+$")){
            errors.rejectValue("name","petName.matches");
        }
        if (!petWeight.matches("(^$|[0-9]*$)")){
            errors.rejectValue("weight","petWeight.matches");
        }
    }
}
