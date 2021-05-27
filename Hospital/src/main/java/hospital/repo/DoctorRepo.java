package hospital.repo;

import hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor findDoctorById(Long id);
}
