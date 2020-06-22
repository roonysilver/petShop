package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "caredetailHaspetdetail")
public class CareDetailHasPetDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CareDetail getCareDetail() {
        return careDetail;
    }

    public void setCareDetail(CareDetail careDetail) {
        this.careDetail = careDetail;
    }

    @ManyToOne
    @JoinColumn(name = "caredetail_id")
    private CareDetail careDetail;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
