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
public class ExperienciaDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    private int duracion;
    //Constructores

    public ExperienciaDto() {
    }

    public ExperienciaDto(String nombre, String descripcion, int duracion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

}