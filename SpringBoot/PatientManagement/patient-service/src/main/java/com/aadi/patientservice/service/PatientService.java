package com.aadi.patientservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aadi.patientservice.dto.PatientResponseDTO;
import com.aadi.patientservice.mapper.PatientMapper;
import com.aadi.patientservice.model.Patient;
import com.aadi.patientservice.repository.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }
}
