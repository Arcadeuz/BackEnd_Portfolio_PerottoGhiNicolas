package com.portfolio.mnpg.Service;

import com.portfolio.mnpg.Entity.Habilidad;
import com.portfolio.mnpg.Repository.HabilidadRepository;

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
public class HabilidadService {

    @Autowired
    HabilidadRepository habilidadRepository;

    public List<Habilidad> list() {
        return habilidadRepository.findAll();
    }

    public List<Habilidad> listByPersonaId(int personaId) {
        return habilidadRepository.findByPersonaId(personaId);
    }

    public void deleteByPersonaId(int personaId) {
        habilidadRepository.deleteAll(habilidadRepository.findByPersonaId(personaId));
    }

    public Optional<Habilidad> getOne(int id) {
        return habilidadRepository.findById(id);
    }

    public Optional<Habilidad> getByNombre(String nombre) {
        return habilidadRepository.findByNombre(nombre);
    }

    public void save(Habilidad habilidad) {
        habilidadRepository.save(habilidad);
    }

    public void delete(int id) {
        habilidadRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return habilidadRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return habilidadRepository.existsByNombre(nombre);
    }
}
