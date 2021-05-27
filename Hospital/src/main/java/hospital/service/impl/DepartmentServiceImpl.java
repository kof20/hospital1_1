package hospital.service.impl;


import hospital.model.Department;
import hospital.repo.DepartmentRepo;
import hospital.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements GeneralService<Department> {
    @Autowired
    DepartmentRepo departmentRepo;

    @Override
    public void save(Department departament) {
        departmentRepo.save(departament);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepo.findDepartmentById(id);
    }

    @Override
    public void delete(Long id) {
        departmentRepo.deleteById(id);
    }
}
