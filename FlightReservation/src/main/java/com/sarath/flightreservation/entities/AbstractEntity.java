package com.sarath.flightreservation.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass   //Acts as a parent class for all other classes id fields
public class AbstractEntity
{

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
