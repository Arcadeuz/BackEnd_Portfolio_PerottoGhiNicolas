package com.portfolio.mnpg.Controller;

import com.portfolio.mnpg.Dto.PersonaDto;
import com.portfolio.mnpg.Entity.Persona;
import com.portfolio.mnpg.Security.Controller.CheckController;
import com.portfolio.mnpg.Security.Controller.Mensaje;
import com.portfolio.mnpg.Service.PersonaService;
import com.portfolio.mnpg.Service.SocialService;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/persona")
//@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    @Autowired
    PersonaService personaService;
    @Autowired
    CheckController checkController;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{personaId}")
    public ResponseEntity<Persona> getById(@PathVariable("personaId") int personaId) {
        if (!personaService.existsById(personaId)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = personaService.getOne(personaId).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(id))) {
                        
            personaService.delete(id);
            return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PersonaDto personaDto) {

        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(personaDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).get();

        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(id))) {
            persona.setNombre(personaDto.getNombre());
            persona.setApellido(personaDto.getApellido());
            persona.setAcercaDeMi(personaDto.getAcercaDeMi());
            persona.setFotoPerfil(personaDto.getFotoPerfil());
            persona.setColor(personaDto.getColor());

            personaService.save(persona);

            return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody PersonaDto personaDto
    ) {
        if (StringUtils.isBlank(personaDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        Persona persona = new Persona(personaDto.getNombre(), personaDto.getApellido(),
                personaDto.getAcercaDeMi(), personaDto.getFotoPerfil(), personaDto.getColor(),username);
        personaService.save(persona);

        return new ResponseEntity(new Mensaje("Persona agregada"), HttpStatus.OK);
    }

    @GetMapping("/miLista")
    public ResponseEntity<List<Persona>> listByPropietario() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        List<Persona> list = personaService.findByPropietario(username);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
