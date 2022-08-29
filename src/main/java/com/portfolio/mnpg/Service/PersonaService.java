package com.portfolio.mnpg.Service;

import com.portfolio.mnpg.Entity.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.mnpg.Repository.PersonaRepository;
import java.util.Optional;

/**
 *
 * @author Nico
 */
@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;
    //-----No me gusta, hacer con crud ---// //Queda Así pq se me acabó el tiempo//
    @Autowired
    EducacionService educacionService;
    @Autowired
    ExperienciaService experienciaService;
    @Autowired
    HabilidadService habilidadService;
    @Autowired
    ProyectoService proyectoService;
    @Autowired
    SocialService socialService;
    //----------------------------------------//       

    public List<Persona> list() {
        return personaRepository.findAll();
    }

    public List<Persona> findByPropietario(String propietario) {
        return personaRepository.findByPropietario(propietario);
    }

    public Optional<Persona> getOne(int id) {
        return personaRepository.findById(id);
    }

    public Optional<Persona> getByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }

    public void save(Persona persona) {
        personaRepository.save(persona);
    }

    public void delete(int id) {
        educacionService.deleteByPersonaId(id);
        experienciaService.deleteByPersonaId(id);
        habilidadService.deleteByPersonaId(id);
        proyectoService.deleteByPersonaId(id);
        socialService.deleteByPersonaId(id);
        personaRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return personaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return personaRepository.existsByNombre(nombre);
    }

}
