package com.sarath.flightreservation.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends AbstractEntity
{
	@NotEmpty
	@NotNull
    private String firstName;

	@NotEmpty
	@NotNull
    private String lastName;
    
    @NotEmpty(message = "Email should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Flag.CASE_INSENSITIVE)
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, message = "Please provide at least 8 charecters long")
    private String password;
}
