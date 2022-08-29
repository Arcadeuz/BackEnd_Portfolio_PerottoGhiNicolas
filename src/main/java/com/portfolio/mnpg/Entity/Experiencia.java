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
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    private int duracion;
    private int personaId;
    //Constructores

    public Experiencia() {
    }

    public Experiencia(String nombre, String descripcion, int duracion, int personaId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.personaId = personaId;
    }
}
