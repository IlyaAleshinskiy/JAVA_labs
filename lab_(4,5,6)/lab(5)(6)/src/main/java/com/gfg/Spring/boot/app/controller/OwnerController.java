package com.gfg.Spring.boot.app.controller;

import com.gfg.Spring.boot.app.model.Owner;
import com.gfg.Spring.boot.app.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/owner/form")
    public String showForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owner-form";
    }

    @PostMapping("/owner/save")
    public String saveOwner(@Valid Owner owner, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "owner-form";
        }
        ownerRepository.save(owner);
        return "redirect:/owner/form?success";
    }
}
