package com.portfolio.mnpg.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Nico
 */
@Getter @Setter
@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Id
    private int id; //Luego camaniar a id, si ya se entiende que persona.id es idPersona

    @NotNull
    @Size(min = 1, max=50, message = "longNombreError")
    private String nombre;

    @NotNull
    @Size(min = 1, max=50, message = "longApellidoError")
    private String apellido;
    
    @Column(length = 1024)
    private String acercaDeMi;
    
    @Column(length = 512)
    private String fotoPerfil;
    
    private String color;
    private String propietario; //No pude hacer que tomar el id de Usuario asi que va el nombre

    public Persona() {
    }

    public Persona(String nombre, String apellido, String acercaDeMi, String fotoPerfil,String color, String propietario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.acercaDeMi = acercaDeMi;
        this.fotoPerfil = fotoPerfil;
        this.color = color;
        this.propietario = propietario;
    }
    
}