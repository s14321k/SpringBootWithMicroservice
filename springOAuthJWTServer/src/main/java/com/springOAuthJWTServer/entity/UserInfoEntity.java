package com.springOAuthJWTServer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_INFO")
public class UserInfoEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(nullable = false, name = "EMAIL_ID", unique = true)
	private String emailId;
	
	@Column(nullable = false, name = "PASSWORD")
    private String password;
    
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(nullable = false, name = "ROLES")
    private String roles;
}
