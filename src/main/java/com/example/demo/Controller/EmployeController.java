package com.yourapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/employes")
public class EmployeController {

    @GetMapping("/ajouter")
    public String showAddForm() {
        return "employes/ajouter-employe";
    }

    @GetMapping("/liste")
    public String showList() {
        return "employes/liste";
    }
}