/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mnpg.Service;

import com.portfolio.mnpg.Entity.Social;
import com.portfolio.mnpg.Repository.SocialRepository;
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
public class SocialService {

    @Autowired
    SocialRepository socialRepository;

    public List<Social> list() {
        return socialRepository.findAll();
    }

    public List<Social> listByPersonaId(int personaId) {
        return socialRepository.findByPersonaId(personaId);
    }

    public void deleteByPersonaId(int personaId) {
        socialRepository.deleteAll(socialRepository.findByPersonaId(personaId));
    }

    public Optional<Social> getOne(int id) {
        return socialRepository.findById(id);
    }

    public Optional<Social> getByNombre(String nombre) {
        return socialRepository.findByNombre(nombre);
    }

    public void save(Social proyecto) {
        socialRepository.save(proyecto);
    }

    public void delete(int id) {
        socialRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return socialRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return socialRepository.existsByNombre(nombre);
    }
}
