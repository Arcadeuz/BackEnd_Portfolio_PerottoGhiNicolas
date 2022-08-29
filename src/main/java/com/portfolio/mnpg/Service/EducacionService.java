/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mnpg.Service;

import com.portfolio.mnpg.Entity.Educacion;
import com.portfolio.mnpg.Repository.EducacionRepository;
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
public class EducacionService {

    @Autowired
    EducacionRepository educacionRepository;

    public List<Educacion> list() {
        return educacionRepository.findAll();
    }
    
    public List<Educacion> listByPersonaId(int personaId) {
        return educacionRepository.findByPersonaId(personaId);
    }
    
    public void deleteByPersonaId(int personaId) {
         educacionRepository.deleteAll(educacionRepository.findByPersonaId(personaId));
    }

    public Optional<Educacion> getOne(int id) {
        return educacionRepository.findById(id);
    }

    public Optional<Educacion> getByNombre(String nombre) {
        return educacionRepository.findByNombre(nombre);
    }

    public void save(Educacion educacion) {
        educacionRepository.save(educacion);
    }

    public void delete(int id) {
        educacionRepository.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return educacionRepository.existsById(id);
    }

    public boolean existsByNombre(String nombreE) {
        return educacionRepository.existsByNombre(nombreE);
    }
}
