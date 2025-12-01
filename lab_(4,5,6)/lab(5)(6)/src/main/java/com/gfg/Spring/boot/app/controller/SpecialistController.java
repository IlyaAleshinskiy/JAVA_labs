package com.gfg.Spring.boot.app.controller;

import com.gfg.Spring.boot.app.model.Specialist;
import com.gfg.Spring.boot.app.repository.SpecialistRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpecialistController {

    @Autowired
    private SpecialistRepository specialistRepository;

    @GetMapping("/specialist/form")
    public String showSpecialistForm(Model model) {
        model.addAttribute("specialist", new Specialist());
        return "specialist-form";
    }

    @PostMapping("/specialist/save")
    public String saveSpecialist(@Valid Specialist specialist, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "specialist-form";
        }
        specialistRepository.save(specialist);
        return "redirect:/specialist/form?success";
    }
}
