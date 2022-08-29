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
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int porcentaje;
    private int personaId;
    //constructores
    
    public Habilidad() {
    }

    public Habilidad(String nombre, int porcentaje, int personaId) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.personaId = personaId;
    }    
}
