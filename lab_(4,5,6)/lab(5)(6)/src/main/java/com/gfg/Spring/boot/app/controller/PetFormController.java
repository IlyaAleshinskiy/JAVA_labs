package com.gfg.Spring.boot.app.controller;

import com.gfg.Spring.boot.app.model.Pet;
import com.gfg.Spring.boot.app.repository.OwnerRepository;
import com.gfg.Spring.boot.app.repository.PetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetFormController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/pet/form")
    public String showPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("owners", ownerRepository.findAll());
        return "pet-form";
    }

    @PostMapping("/pet/save")
    public String savePet(@Valid Pet pet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("owners", ownerRepository.findAll());
            return "pet-form";
        }
        petRepository.save(pet);
        return "redirect:/pet/form?success";
    }

    @GetMapping("/pet/success")
    public String success() {
        return "success";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}