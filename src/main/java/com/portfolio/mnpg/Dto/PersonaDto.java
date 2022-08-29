/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mnpg.Dto;

import com.portfolio.mnpg.Entity.Persona;
import com.portfolio.mnpg.Service.PersonaService;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nico
 */
@Getter @Setter
public class PersonaDto {
    private String nombre;
    private String apellido;
    private String acercaDeMi;
    private String fotoPerfil;
    private String color;

    public PersonaDto() {
    }

    public PersonaDto(String nombre, String apellido, String acercaDeMi, String fotoPerfil, String color) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.acercaDeMi = acercaDeMi;
        this.fotoPerfil = fotoPerfil;
        this.color = color;
    }
    
}