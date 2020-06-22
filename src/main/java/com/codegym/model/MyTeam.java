package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "myteam")
public class MyTeam implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private String position;
    private String description;
    private String image;
    private boolean softDelete;

    public MyTeam() {
    }

    public boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }

    @OneToMany(targetEntity = PetDetailHasMyTeam.class)
    private Set<PetDetailHasMyTeam> PetDetailHasMyTeam;

    public Set<com.codegym.model.PetDetailHasMyTeam> getPetDetailHasMyTeam() {
        return PetDetailHasMyTeam;
    }

    public void setPetDetailHasMyTeam(Set<com.codegym.model.PetDetailHasMyTeam> petDetailHasMyTeam) {
        PetDetailHasMyTeam = petDetailHasMyTeam;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return MyTeam.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MyTeam myTeam = (MyTeam) target;

        String teamName = myTeam.getName();

        ValidationUtils.rejectIfEmpty(errors, "name", "teamName.empty");

        if (!teamName.matches("^[a-zA-ZÀ-ỹ-\\s]+$")){
            errors.rejectValue("name","teamName.matches");
        }
    }
}
