package hospital.repo;

import hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "patient", path = "patient")
public interface PatientRepo extends JpaRepository<Patient, Long> {
    Patient findPatientById(Long id);
    Patient findPatientByName(String name);
}
