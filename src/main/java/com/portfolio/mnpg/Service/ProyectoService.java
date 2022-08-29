package com.portfolio.mnpg.Service;

import com.portfolio.mnpg.Entity.Proyecto;
import com.portfolio.mnpg.Repository.ProyectoRepository;
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
public class ProyectoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    public List<Proyecto> list() {
        return proyectoRepository.findAll();
    }

    public List<Proyecto> listByPersonaId(int personaId) {
        return proyectoRepository.findByPersonaId(personaId);
    }

    public void deleteByPersonaId(int personaId) {
        proyectoRepository.deleteAll(proyectoRepository.findByPersonaId(personaId));
    }

    public Optional<Proyecto> getOne(int id) {
        return proyectoRepository.findById(id);
    }

    public Optional<Proyecto> getByNombre(String nombre) {
        return proyectoRepository.findByNombre(nombre);
    }

    public void save(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    public void delete(int id) {
        proyectoRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return proyectoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return proyectoRepository.existsByNombre(nombre);
    }
}
