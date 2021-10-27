package pg.przemek.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.przemek.lab1.entity.Doctor;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>
{

    @Override
    Optional<Doctor> findById(Long id);

    @Override
    List<Doctor> findAll();

    @Override
    Doctor save(Doctor entity);

    @Override
    void deleteById(Long id);


}
