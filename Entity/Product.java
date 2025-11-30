package com.example.MySQLIntegration.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "Category must not be blank")
    private String category;

    @Positive(message = "price should be positive")
    private Double price;

    @Positive(message = "quantity must be positive")
    private Integer quantity;

    private Boolean active;

    private LocalDate date;

    private LocalDate updatedDate;


}
