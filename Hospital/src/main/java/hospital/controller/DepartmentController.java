package hospital.controller;

import hospital.model.Department;
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
public class DepartmentController {

    @Qualifier("departmentServiceImpl")
    @Autowired
    GeneralService generalService;

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Department department = (Department) this.generalService.findById(id);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @RequestMapping(value = "/department", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        HttpHeaders headers = new HttpHeaders();
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.generalService.save(department);
        return new ResponseEntity<>(department, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Long id) {
        Department department = (Department) this.generalService.findById(id);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.generalService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = this.generalService.findAll();

        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}