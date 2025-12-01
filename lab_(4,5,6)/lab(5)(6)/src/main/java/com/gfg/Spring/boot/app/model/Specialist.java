package com.gfg.Spring.boot.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialists")
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    // select: surgeon / therapist / dentist / dermatologist
    @NotBlank(message = "Специализация обязательна")
    private String specialization;

    // radio: full-time / part-time
    @NotBlank(message = "Тип занятости обязателен")
    private String employmentType; // full-time, part-time

    // checkbox
    private boolean acceptsNewPatients = true;

    @ManyToMany(mappedBy = "specialists")
    private Set<MedicalRecord> medicalRecords = new HashSet<>();

    // === constructors ===
    public Specialist() {}

    // === getters / setters ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }

    public boolean isAcceptsNewPatients() { return acceptsNewPatients; }
    public void setAcceptsNewPatients(boolean acceptsNewPatients) { this.acceptsNewPatients = acceptsNewPatients; }

    public Set<MedicalRecord> getMedicalRecords() { return medicalRecords; }
    public void setMedicalRecords(Set<MedicalRecord> records) { this.medicalRecords = records; }
}