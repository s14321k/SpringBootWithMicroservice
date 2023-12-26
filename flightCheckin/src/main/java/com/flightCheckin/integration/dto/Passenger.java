package com.flightCheckin.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger
{
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
}
