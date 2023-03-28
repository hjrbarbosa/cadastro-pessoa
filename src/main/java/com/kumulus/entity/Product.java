package com.kumulus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    private int id;
    private String code;

    @Column(name = "name", length = 150, nullable = false)
    private String name;
    private String description;
    private String image;
    private double price;
    private String category;
    private int quantity;
    private int rating;

    public Product() {
    }

    public Product(int id, String code, String name, String description, String image, double price, String category, int quantity, int rating) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        if (code == null) {
            return other.code == null;
        }
        else {
            return code.equals(other.code);
        }
    }

    @Override
    public Product clone() {
        return new Product(getId(), getCode(), getName(), getDescription(), getImage(), getPrice(), getCategory(), getQuantity(),
                getRating());
    }
}
