package com.portfolio.mnpg.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nico
 */
@Getter @Setter
@Entity
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    private int personaId;
  
    //constructores
    
    public Educacion() {
    }

    public Educacion(String nombre, String descripcion, int personaId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.personaId = personaId;
    }    
}