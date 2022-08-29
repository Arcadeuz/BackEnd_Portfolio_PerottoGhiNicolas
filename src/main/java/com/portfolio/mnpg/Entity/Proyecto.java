package com.portfolio.mnpg.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nico
 */
@Getter @Setter
@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    private String fotoProyecto;
    private String  fechaInicio;
    private String  fechaFin;
    private int personaId;
    //constructores
    
    public Proyecto() {
    }

    public Proyecto(String nombre, String descripcion, String fotoProyecto, String fechaInicio, String fechaFin, int personaId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotoProyecto = fotoProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.personaId = personaId;
    }
}