/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.mnpg.Repository;

import com.portfolio.mnpg.Entity.Habilidad;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nico
 */
public interface HabilidadRepository extends JpaRepository<Habilidad, Integer>{
    public Optional<Habilidad> findByNombre(String nombre);
    public List<Habilidad> findByPersonaId(int personaId);
    public boolean existsByNombre(String nombre);
}
