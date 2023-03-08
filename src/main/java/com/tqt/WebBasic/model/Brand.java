package com.tqt.WebBasic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameBrand;

    public Brand(int id, String nameBrand) {
        this.id = id;
        this.nameBrand = nameBrand;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", nameBrand='" + nameBrand + '\'' +
                '}';
    }

    public Brand() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }
}
