package com.springBATCH.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="address", schema="addr_batch")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address 
{
	@Id
	@Column(name = "column1")
	private String column1;
	
	@Column(name = "column2")
	private String column2;
	
}
