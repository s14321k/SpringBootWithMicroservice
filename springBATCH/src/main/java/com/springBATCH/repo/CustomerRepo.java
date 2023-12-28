package com.springBATCH.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBATCH.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Serializable>
{
	
}
