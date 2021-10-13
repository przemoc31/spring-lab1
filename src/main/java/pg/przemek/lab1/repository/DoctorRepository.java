package pg.przemek.lab1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pg.przemek.lab1.Storage.Storage;
import pg.przemek.lab1.entity.Doctor;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepository
{
    private Storage storage;

    @Autowired
    public DoctorRepository(Storage storage)
    {
        this.storage = storage;
    }

    public Optional<Doctor> find(int id)
    {
        return this.storage.findDoctor(id);
    }

    public List<Doctor> findAll() {
        return storage.findAllDoctors();
    }

    public void save(Doctor entity) { storage.saveDoctor(entity); }

    public void delete(Doctor entity) { storage.deleteDoctor(entity.getId()); }
}
