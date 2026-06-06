package com.example.demo.Controller;

import com.example.demo.Repository.EmployeRepository;
import com.example.demo.model.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeController {

    @Autowired
    private EmployeRepository repo;

    @GetMapping("/")
    public String home() {
        return "redirect:/employes/ajouter";
    }

    // PAGE AJOUTER
    @GetMapping("/employes/ajouter")
    public String showAddForm() {
        return "employes/ajouter-employe";
    }

    // save employee
    @PostMapping("/employes/save")
    public String saveEmploye(@RequestParam String nom,
                              @RequestParam String prenom,
                              @RequestParam String email,
                              @RequestParam String telephone,
                              @RequestParam String poste,
                              @RequestParam String dateEmbauche) {

        Employe emp = new Employe();
        emp.setNom(nom);
        emp.setPrenom(prenom);
        emp.setEmail(email);
        emp.setTelephone(telephone);
        emp.setPoste(poste);
        emp.setDateEmbauche(dateEmbauche);

        repo.save(emp);

        return "redirect:/employes/ajouter";
    }

    // LIST EMPLOYES
    @GetMapping("/employes/liste")
    public String list(Model model) {

        List<Employe> list = repo.findAll();

        model.addAttribute("employes", list);

        return "employes/liste-employes";
    }

    //suprimer
    @GetMapping("/employes/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/employes/liste";
    }

    //modifier
    @GetMapping("/employes/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        Employe emp = repo.findById(id).orElse(null);

        model.addAttribute("employe", emp);

        return "employes/edit-employe";
    }

    //update et save
    @PostMapping("/employes/update")
    public String updateEmploye(@RequestParam Long id,
                                @RequestParam String nom,
                                @RequestParam String prenom,
                                @RequestParam String email,
                                @RequestParam String telephone,
                                @RequestParam String poste,
                                @RequestParam String dateEmbauche) {

        Employe emp = repo.findById(id).orElse(null);

        if (emp != null) {
            emp.setNom(nom);
            emp.setPrenom(prenom);
            emp.setEmail(email);
            emp.setTelephone(telephone);
            emp.setPoste(poste);
            emp.setDateEmbauche(dateEmbauche);

            repo.save(emp);
        }

        return "redirect:/employes/liste";
    }
}