package pg.przemek.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;
import pg.przemek.lab1.repository.DepartmentRepository;
import pg.przemek.lab1.repository.DepartmentRepositoryClass;

import java.util.List;
import java.util.NoSuchElementException;
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

    public List<Department> findAll() { return departmentRepository.findAll(); }

    @Transactional
    public Department save(Department department) { return departmentRepository.save(department); }

    @Transactional
    public void delete(String name) { departmentRepository.deleteById(name); }

    @Transactional
    public void update(Department department) { departmentRepository.save(department); }
}
