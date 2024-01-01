package com.springBATCH.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMERS_INFO", schema = "batch")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@Column(name = "CUSTOMER_ID")
	private int id;
	
	@NotBlank(message = "First Name Cannot be empty")
	@Size(max = 50, message = "First name cannot exceed 50 charecters")
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Size(max = 50, message = "First name cannot exceed 50 charecters")
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@NotBlank(message = "Email cannot be empty")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
//    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", 
//    		message = "Invalid email address format")
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "CONTACT")
	private String contactNo;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "DOB")
	private String dob;

}
