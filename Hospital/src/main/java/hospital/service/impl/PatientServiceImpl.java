package hospital.service.impl;

import hospital.model.Patient;
import hospital.repo.PatientRepo;
import hospital.service.PatientService;
import hospital.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepo patientRepo;

    @Override
    public void save(Patient patient) {
        patientRepo.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    @Override
    public Patient findById(Long id) {
        return patientRepo.findPatientById(id);
    }

    @Override
    public void delete(Long id) {
        patientRepo.deleteById(id);
    }

    @Override
    public Patient findByName(String name) {
        return patientRepo.findPatientByName(name);
    }
}

