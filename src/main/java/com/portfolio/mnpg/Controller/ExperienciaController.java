/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mnpg.Controller;

import com.portfolio.mnpg.Dto.ExperienciaDto;
import com.portfolio.mnpg.Entity.Experiencia;
import com.portfolio.mnpg.Security.Controller.CheckController;
import com.portfolio.mnpg.Security.Controller.Mensaje;
import com.portfolio.mnpg.Service.ExperienciaService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author Nico
 */
@RestController
@RequestMapping("/experiencia")
//@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {

    @Autowired
    ExperienciaService experienciaService;
    @Autowired
    CheckController checkController;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = experienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = experienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }

        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(experienciaService.getOne(id).get().getPersonaId()))) {
            experienciaService.delete(id);
            return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/crear/{personaId}")
    public ResponseEntity<?> create(@PathVariable("personaId") int personaId, @RequestBody ExperienciaDto experienciaDto) {
        if (StringUtils.isBlank(experienciaDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (checkController.checkIfOwner(personaId)) {
            Experiencia experiencia = new Experiencia(experienciaDto.getNombre(), experienciaDto.getDescripcion(), experienciaDto.getDuracion(), personaId);
            experienciaService.save(experiencia);
            return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lista/{personaId}")
    public ResponseEntity<List<Experiencia>> listByPersonaId(@PathVariable("personaId") int personaId) {
        List<Experiencia> list = experienciaService.listByPersonaId(personaId);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienciaDto experienciaDto) {
        //Validamos si existe el ID
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        //No puede estar vacio
        if (StringUtils.isBlank(experienciaDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = experienciaService.getOne(id).get();
        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(experiencia.getPersonaId()))) {
            experiencia.setNombre(experienciaDto.getNombre());
            experiencia.setDescripcion((experienciaDto.getDescripcion()));
            experiencia.setDuracion((experienciaDto.getDuracion()));
            experienciaService.save(experiencia);
            return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }

    }
}
