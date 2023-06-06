package com.sarath.location.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("LocCon")
public class LocationController
{
    @GetMapping("/ShowCreate")
    public String showCreate()
    {
        return "CreateLocation";
    }
}
