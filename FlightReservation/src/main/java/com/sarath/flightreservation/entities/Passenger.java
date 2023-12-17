package com.sarath.flightreservation.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Passenger extends AbstractEntity
{
	@NotEmpty
	@NotNull
    private String firstName;
	@NotEmpty
	@NotNull
    private String lastName;
	@NotNull
    private String middleName;
    @NotEmpty(message = "Email should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Flag.CASE_INSENSITIVE)
    private String email;
    @NotEmpty(message = "Phone number should not be empty")
    private String phone;
}
