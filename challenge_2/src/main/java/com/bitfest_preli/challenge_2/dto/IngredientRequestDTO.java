package com.bitfest_preli.challenge_2.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
public class IngredientRequestDTO {
    private String name;
    private double quantity;
    private String unit;

    public double getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }
}
