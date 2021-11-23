package pg.przemek.lab1.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.przemek.lab1.department.entity.Department;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String>
{

    @Override
    Optional<Department> findById(String name);

    @Override
    List<Department> findAll();

    @Override
    Department save(Department entity);

    @Override
    void deleteById(String name);
}
