package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.model.Sell;
import com.example.demo.repo.SellRepository;

@Service
public class SellService {

  private final SellRepository sellRepository;
 
  @Autowired
  SellService(SellRepository sellRepository) {
    this.sellRepository = sellRepository;
  }

  public List<Sell> getAllSell() {
    return sellRepository.findAll();
  }
 
  public Sell createNewSell(Sell sell) {
    if (sell.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Sell");
    }
    return sellRepository.save(sell);
  }
 
  public Sell getSellById(Long id) {
    return sellRepository
            .findById(id)
            .orElseThrow(() -> new ElementNotFoundException("Sell with ID not found"));
  }
}
