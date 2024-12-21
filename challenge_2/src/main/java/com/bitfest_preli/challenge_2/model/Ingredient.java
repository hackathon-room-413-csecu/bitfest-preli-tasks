package com.bitfest_preli.challenge_2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Primary key

    private String name; // Ingredient name

    private double quantity; // Quantity of the ingredient

    private String unit; // Unit of measurement (e.g., "grams", "liters")

    @LastModifiedDate
    @Column(name = "last_updated")
    private Timestamp lastModifiedAt;

    public double getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

