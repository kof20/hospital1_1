package hospital.service.impl;


import hospital.model.Doctor;
import hospital.repo.DoctorRepo;
import hospital.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements GeneralService<Doctor> {
    @Autowired
    DoctorRepo doctorRepo;

    @Override
    public void save(Doctor doctor) {
        doctorRepo.save(doctor);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepo.findAll();
    }

    @Override
    public Doctor findById(Long id) {
        return doctorRepo.findDoctorById(id);
    }

    @Override
    public void delete(Long id) {
        doctorRepo.deleteById(id);
    }
}
