/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mnpg.Dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nico
 */
@Getter @Setter
public class SocialDto {
    private String nombre;
    private String link;
    private int personaId;

    public SocialDto() {
    }

    public SocialDto(String nombre, String link, int personaId) {
        this.nombre = nombre;
        this.link = link;
        this.personaId = personaId;
    }
}
