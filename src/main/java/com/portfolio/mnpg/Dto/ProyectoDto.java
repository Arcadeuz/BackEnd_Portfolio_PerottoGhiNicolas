/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mnpg.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nico
 */
@Getter @Setter
public class ProyectoDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String fotoProyecto;
    private String fechaInicio;
    private String fechaFin;
    
    //constructores

    public ProyectoDto() {
    }

    public ProyectoDto(String nombre, String descripcion, String fotoProyecto, String fechaInicio, String fechaFin) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotoProyecto = fotoProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
 
}
