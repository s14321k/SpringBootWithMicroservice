package com.sarath.flightreservation.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	@NotEmpty(message = "First name should not be empty")
	@NotNull(message = "Last name should not be empty")
	private String firstName;

	@NotEmpty(message = "Last name should not be empty")
	@NotNull(message = "Last name should not be empty")
	private String lastName;

	@NotEmpty(message = "Email should not be empty")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE, message = "Invalid email format")
	private String email;

	@NotEmpty(message = "Password should not be empty")
	@Size(min = 8, message = "Please provide at least 8 characters")
	private String password;
    
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
