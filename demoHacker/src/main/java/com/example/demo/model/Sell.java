package com.example.demo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Builder
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
 
    @Column(name = "product_name")
    @NotNull(message ="Product name is mandatory")
    private String productName;
 
    @Email(message = "Invalid customer email")
    @Column(name = "customer_email")
    private String customerEmail;
 
    @Positive(message = "Value should be none negative")
    @Column (name = "buying_price")
    private Integer buyingPrice;

    @Positive(message = "Value should be none negative")
    @Column(name = "selling_price")
    private Integer sellingPrice;
}