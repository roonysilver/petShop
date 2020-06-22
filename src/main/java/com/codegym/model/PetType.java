package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pettype")
public class PetType implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private boolean softDelete;

    public PetType() {
    }

    public boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }

    @OneToMany(targetEntity = PetKind.class)
    private List<PetKind> petKinds;

    public List<PetKind> getPetKinds() {
        return petKinds;
    }

    public void setPetKinds(List<PetKind> petKinds) {
        this.petKinds = petKinds;
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

    @Override
    public boolean supports(Class<?> clazz) {
        return PetType.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    PetType petType = (PetType) target;
    String petTypeName = petType.getName();
    ValidationUtils.rejectIfEmpty(errors,"name", "petTypeName.empty");
        if (!petTypeName.matches(
                "^[a-zA-ZÀ-ỹ-\\s]+$")){
            errors.rejectValue("name","petTypeName.matches");
        }
    }
}
