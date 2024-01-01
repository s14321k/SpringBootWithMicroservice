package com.springBATCH.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBATCH.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Serializable>{

}
