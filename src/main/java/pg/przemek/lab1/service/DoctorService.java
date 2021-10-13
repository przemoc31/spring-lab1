package pg.przemek.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.przemek.lab1.entity.Doctor;
import pg.przemek.lab1.repository.DoctorRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DoctorService
{
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) { this.doctorRepository = doctorRepository; }

    public Optional<Doctor> find(int id) {
        return doctorRepository.find(id);
    }

    public List<Doctor> findAll() { return doctorRepository.findAll(); }

    public void save(Doctor doctor) { doctorRepository.save(doctor); }

    public void delete(int id) { doctorRepository.delete(doctorRepository.find(id).orElseThrow(() -> new NoSuchElementException("Doctor with such an id does not exist"))); }
}
