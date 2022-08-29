package com.portfolio.mnpg.Service;

import com.portfolio.mnpg.Entity.Experiencia;
import com.portfolio.mnpg.Repository.ExperienciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nico
 */
@Service
@Transactional
public class ExperienciaService {

    @Autowired
    ExperienciaRepository experienciaRepository;

    public List<Experiencia> list() {
        return experienciaRepository.findAll();
    }

    public List<Experiencia> listByPersonaId(int personaId) {
        return experienciaRepository.findByPersonaId(personaId);
    }

    public void deleteByPersonaId(int personaId) {
        experienciaRepository.deleteAll(experienciaRepository.findByPersonaId(personaId));
    }

    public Optional<Experiencia> getOne(int id) {
        return experienciaRepository.findById(id);
    }

    public Optional<Experiencia> getByNombre(String nombre) {
        return experienciaRepository.findByNombre(nombre);
    }

    public void save(Experiencia experiencia) {
        experienciaRepository.save(experiencia);
    }

    public void delete(int id) {
        experienciaRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return experienciaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return experienciaRepository.existsByNombre(nombre);
    }
}
