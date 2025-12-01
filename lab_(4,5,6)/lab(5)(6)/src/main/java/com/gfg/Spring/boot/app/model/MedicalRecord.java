package com.gfg.Spring.boot.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Диагноз обязателен")
    private String diagnosis;

    // select: routine / emergency / surgery
    @NotBlank(message = "Тип визита обязателен")
    private String visitType; // routine, emergency, surgery

    // radio: yes / no → сохраняем как boolean
    private boolean requiresFollowUp = false;

    // checkbox
    private boolean medicationPrescribed = false;

    @PastOrPresent(message = "Дата не может быть в будущем")
    private LocalDate visitDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToMany
    @JoinTable(
            name = "record_specialist",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "specialist_id")
    )
    private Set<Specialist> specialists = new HashSet<>();

    // === constructors ===
    public MedicalRecord() {}

    // === getters / setters ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getVisitType() { return visitType; }
    public void setVisitType(String visitType) { this.visitType = visitType; }

    public boolean isRequiresFollowUp() { return requiresFollowUp; }
    public void setRequiresFollowUp(boolean requiresFollowUp) { this.requiresFollowUp = requiresFollowUp; }

    public boolean isMedicationPrescribed() { return medicationPrescribed; }
    public void setMedicationPrescribed(boolean medicationPrescribed) { this.medicationPrescribed = medicationPrescribed; }

    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public Pet getPet() { return pet; }
    public void setPet(Pet pet) { this.pet = pet; }

    public Set<Specialist> getSpecialists() { return specialists; }
    public void setSpecialists(Set<Specialist> specialists) { this.specialists = specialists; }

    public void addSpecialist(Specialist specialist) {
        specialists.add(specialist);
        specialist.getMedicalRecords().add(this);
    }

    public void removeSpecialist(Specialist specialist) {
        specialists.remove(specialist);
        specialist.getMedicalRecords().remove(this);
    }
}