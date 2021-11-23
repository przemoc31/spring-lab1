package pg.przemek.lab1.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.department.repository.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentService
{

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) { this.departmentRepository = departmentRepository; }

    public Optional<Department> find(String name)
    {
        return departmentRepository.findById(name);
    }

    @Transactional
    public void save(Department department) { departmentRepository.save(department); }

    @Transactional
    public void delete(String name) { departmentRepository.deleteById(name); }
}
