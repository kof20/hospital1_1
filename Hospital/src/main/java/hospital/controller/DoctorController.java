package hospital.controller;

import hospital.model.Doctor;
import hospital.model.Patient;
import hospital.service.GeneralService;
import hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Qualifier("doctorServiceImpl")
    @Autowired
    GeneralService generalService;

    @Qualifier("patientServiceImpl")
    @Autowired
    PatientService patientService;

    @RequestMapping(value = "/doctor/patient/{pName}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> getPatientByDoctor(@PathVariable(value = "pName") String pName) {
        if (pName == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Doctor doctor =  this.patientService.findByName(pName).getDoctor();

        if (pName == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        doctor.toString();
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Doctor doctor = (Doctor) this.generalService.findById(id);

        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        HttpHeaders headers = new HttpHeaders();
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.generalService.save(doctor);
        return new ResponseEntity<>(doctor, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/doctor/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") Long id) {
        Doctor doctor = (Doctor) this.generalService.findById(id);

        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.generalService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctor = this.generalService.findAll();

        if (doctor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}