/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mnpg.Controller;
import com.portfolio.mnpg.Dto.SocialDto;
import com.portfolio.mnpg.Entity.Social;
import com.portfolio.mnpg.Security.Controller.CheckController;
import com.portfolio.mnpg.Security.Controller.Mensaje;
import com.portfolio.mnpg.Service.SocialService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Nico
 */
@RestController
@RequestMapping("/social")
public class SocialController {
        @Autowired
    SocialService socialService;
    @Autowired
    CheckController checkController;
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Social>> list() {
        List<Social> list = socialService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Social> getById(@PathVariable("id") int id) {
        if (!socialService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Social educacion = socialService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!socialService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(socialService.getOne(id).get().getPersonaId()))) {
            socialService.delete(id);
            return new ResponseEntity(new Mensaje("Social eliminada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/crear/{personaId}")
    public ResponseEntity<?> create(@PathVariable("personaId") int personaId, @RequestBody SocialDto socialDto) {
        if (StringUtils.isBlank(socialDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (checkController.checkIfOwner(personaId)) {
            Social social = new Social(socialDto.getNombre(), socialDto.getLink(), personaId);
            socialService.save(social);
            return new ResponseEntity(new Mensaje("Social creada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/lista/{personaId}")
    public ResponseEntity<List<Social>> listByPersonaId(@PathVariable("personaId") int personaId) {
        List<Social> list = socialService.listByPersonaId(personaId);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody SocialDto socialDto) {
        if (!socialService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(socialDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Social social = socialService.getOne(id).get();
        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(social.getPersonaId()))) {
            social.setNombre(socialDto.getNombre());
            social.setLink(socialDto.getLink());
            socialService.save(social);
            return new ResponseEntity(new Mensaje("Social actualizada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);

        }
    }
    
}
