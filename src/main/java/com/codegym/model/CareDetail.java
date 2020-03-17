package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "caredetail")
public class CareDetail implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private String price;
    private String time;
    @OneToMany(targetEntity = CareDetailHasPetDetail.class)
    private List<CareDetailHasPetDetail> careDetailHasPetDetails;

    public List<CareDetailHasPetDetail> getCareDetailHasPetDetails() {
        return careDetailHasPetDetails;
    }

    public void setCareDetailHasPetDetails(List<CareDetailHasPetDetail> careDetailHasPetDetails) {
        this.careDetailHasPetDetails = careDetailHasPetDetails;
    }

    @ManyToOne
    @JoinColumn(name = "careType_id")
    private CareType careType;

    public CareType getCareType() {
        return careType;
    }

    public void setCareType(CareType careType) {
        this.careType = careType;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CareDetail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CareDetail careDetail = (CareDetail) target;
        String careDetailName = careDetail.getName();
        String careDetailPrice = careDetail.getPrice();
        String careDetailTime = careDetail.getTime();
        ValidationUtils.rejectIfEmpty(errors, "name", "careDetailName.empty");
        ValidationUtils.rejectIfEmpty(errors, "price", "careDetailPrice.empty");
        ValidationUtils.rejectIfEmpty(errors, "time", "careDetailTime.empty");

        if (!careDetailPrice.matches("^$|[0-9]*$")) {
            errors.rejectValue("price", "careDetailPrice.matches");
        }
        if (!careDetailName.matches("^$|[A-Za-z]*$")) {
            errors.rejectValue("name", "careDetailName.matches");
        }
    }
}
