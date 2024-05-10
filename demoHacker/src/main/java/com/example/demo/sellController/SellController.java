package com.example.demo.sellController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.model.Sell;
import com.example.demo.service.SellService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/sell")
//@Validated
public class SellController {
    private final SellService sellService;
 
    @Autowired
    public SellController(SellService sellService) {
        this.sellService = sellService;
    }
 
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Sell> getAllSell() {
        return sellService.getAllSell();
    }
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sell createSell(@Valid @RequestBody Sell sell) {
        return sellService.createNewSell(sell);
    }
 
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Sell getSellById(@PathVariable Long id) {
        return sellService.getSellById(id);
    }
 
   
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Map<String, String> handleBugs(MethodArgumentNotValidException ex, WebRequest request) {
    	System.out.println("Exception handling");
        Map<String, String> errors = new HashMap();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//    	return userRepository.findById(id)                 
//    			.map(user -> {                     
//    				user.setName(updatedUser.getName());                     
//    				user.setEmail(updatedUser.getEmail());                     
//    				// Update other fields as neededreturn userRepository.save(user);
//    				})                 
//    			.orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
//    }
}
