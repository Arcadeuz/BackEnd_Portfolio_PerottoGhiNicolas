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
public class EducacionDto  {
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    
    public EducacionDto() {
    }

    public EducacionDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


}