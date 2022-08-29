package com.portfolio.mnpg.Repository;

import com.portfolio.mnpg.Entity.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nico
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer> {
    public Optional<Persona> findByNombre(String nombre);
    public List<Persona> findByPropietario(String propietario);
    public boolean existsByNombre(String nombre);
}
