package hospital.service;

import hospital.model.Patient;

public interface PatientService extends GeneralService<Patient> {

    Patient findByName(String name);
}
