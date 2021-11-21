package pg.przemek.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.przemek.lab1.entity.Doctor;
import pg.przemek.lab1.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService
{

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) { this.doctorRepository = doctorRepository; }

    public Optional<Doctor> find(Long id) {
        return doctorRepository.findById(id);
    }

    public List<Doctor> findAll() { return doctorRepository.findAll(); }

    @Transactional
    public Doctor save(Doctor doctor) { return doctorRepository.save(doctor); }

    @Transactional
    public void delete(Long id) { doctorRepository.deleteById(id); }

    @Transactional
    public void update(Doctor doctor) { doctorRepository.save(doctor); }
}
