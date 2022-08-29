/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.mnpg.Repository;

import com.portfolio.mnpg.Entity.Social;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nico
 */
public interface SocialRepository extends JpaRepository<Social, Integer>{
    public Optional<Social> findByNombre(String nombre);
    public List<Social> findByPersonaId(int personaId);
    public boolean existsByNombre(String nombre);
}
