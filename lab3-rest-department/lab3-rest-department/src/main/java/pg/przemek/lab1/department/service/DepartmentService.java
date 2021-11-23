package pg.przemek.lab1.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.department.event.repository.DepartmentEventRepository;
import pg.przemek.lab1.department.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService
{

    private DepartmentRepository departmentRepository;

    private DepartmentEventRepository eventRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentEventRepository eventRepository)
    {
        this.departmentRepository = departmentRepository;
        this.eventRepository = eventRepository;
    }

    public Optional<Department> find(String name)
    {
        return departmentRepository.findById(name);
    }

    public List<Department> findAll() { return departmentRepository.findAll(); }

    @Transactional
    public void save(Department department)
    {
        departmentRepository.save(department);
        eventRepository.create(department);
    }

    @Transactional
    public void delete(Department department)
    {
        departmentRepository.delete(department);
        eventRepository.delete(department);
    }

    @Transactional
    public void update(Department department) { departmentRepository.save(department); }
}
