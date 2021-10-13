package pg.przemek.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.repository.DepartmentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DepartmentService
{
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) { this.departmentRepository = departmentRepository; }

    public Optional<Department> find(String name) {
        return departmentRepository.find(name);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public void save(Department department) { departmentRepository.save(department); }

    public void delete(String name) { departmentRepository.delete(departmentRepository.find(name).orElseThrow(() -> new NoSuchElementException("Department with such a name does not exist"))); }
}
