/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.mnpg.Repository;

import com.portfolio.mnpg.Entity.Educacion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nico
 */
@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Integer>{
 
    public Optional<Educacion> findByNombre(String nombre);
    public List<Educacion> findByPersonaId(int personaId);
    public boolean existsByNombre(String nombre);
}
