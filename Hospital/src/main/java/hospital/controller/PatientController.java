package hospital.controller;

import hospital.model.Patient;
import hospital.service.PatientService;
import hospital.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Qualifier("patientServiceImpl")
    @Autowired
    GeneralService generalService;

    @Qualifier("patientServiceImpl")
    @Autowired
    PatientService patientService;

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Patient patient = (Patient) this.generalService.findById(id);

        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> getPatientByName(@RequestParam(value = "name") String name) {
        if (name == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Patient patient = this.patientService.findByName(name);

        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        HttpHeaders headers = new HttpHeaders();
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.generalService.save(patient);
        return new ResponseEntity<>(patient, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") Long id) {
        Patient patient = (Patient) this.generalService.findById(id);

        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.generalService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = this.generalService.findAll();

        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}