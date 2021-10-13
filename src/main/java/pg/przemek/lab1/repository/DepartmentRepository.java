package pg.przemek.lab1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pg.przemek.lab1.Storage.Storage;
import pg.przemek.lab1.entity.Department;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository
{
    private Storage storage;

    @Autowired
    public DepartmentRepository(Storage storage)
    {
        this.storage = storage;
    }

    public Optional<Department> find(String name) { return this.storage.findDepartment(name); }

    public List<Department> findAll() { return storage.findAllDepartments(); }

    public void save(Department entity) { storage.saveDepartment(entity); }

    public void delete(Department entity) { storage.deleteDepartment(entity.getName()); }

}
