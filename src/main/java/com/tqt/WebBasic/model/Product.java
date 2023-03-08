package com.tqt.WebBasic.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idBrand;
    private String nameProduct;
    private int price;
    private String description;

    public Product() {
    }

    public Product(int id, int idBrand, String nameProduct, int price, String description) {
        this.id = id;
        this.idBrand = idBrand;
        this.nameProduct = nameProduct;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", idBrand=" + idBrand +
                ", nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
