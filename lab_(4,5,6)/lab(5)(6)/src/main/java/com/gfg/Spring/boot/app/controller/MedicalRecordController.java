package com.gfg.Spring.boot.app.controller;

import com.gfg.Spring.boot.app.model.MedicalRecord;
import com.gfg.Spring.boot.app.repository.MedicalRecordRepository;
import com.gfg.Spring.boot.app.repository.PetRepository;
import com.gfg.Spring.boot.app.repository.SpecialistRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.WebDataBinder;

import java.beans.PropertyEditorSupport;
import java.util.Set;

@Controller
public class MedicalRecordController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private SpecialistRepository specialistRepository;

    @GetMapping("/record/form")
    public String showRecordForm(Model model) {
        model.addAttribute("record", new MedicalRecord());
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("specialists", specialistRepository.findAll());
        return "medical-record-form";
    }

    @PostMapping("/record/save")
    public String saveRecord(@Valid MedicalRecord record, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pets", petRepository.findAll());
            model.addAttribute("specialists", specialistRepository.findAll());
            return "medical-record-form";
        }
        medicalRecordRepository.save(record);
        return "redirect:/record/form?success";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(
                Set.class,
                "specialists",
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
                        setValue(specialistRepository.findAllById(
                                java.util.Arrays.stream(text.split(","))
                                        .map(Long::parseLong)
                                        .toList()
                        ));
                    }
                }
        );
    }
}