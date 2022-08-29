package com.portfolio.mnpg.Controller;

import com.portfolio.mnpg.Dto.HabilidadDto;
import com.portfolio.mnpg.Entity.Habilidad;
import com.portfolio.mnpg.Security.Controller.CheckController;
import com.portfolio.mnpg.Security.Controller.Mensaje;
import com.portfolio.mnpg.Service.HabilidadService;

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
@RequestMapping("/habilidad")
public class HabilidadController {

    @Autowired
    HabilidadService habilidadService;
    @Autowired
    CheckController checkController;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list() {
        List<Habilidad> list = habilidadService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") int id) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Habilidad habilidad = habilidadService.getOne(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(habilidadService.getOne(id).get().getPersonaId()))) {
            habilidadService.delete(id);
            return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/crear/{personaId}")
    public ResponseEntity<?> create(@PathVariable("personaId") int personaId, @RequestBody HabilidadDto habilidadDto) {
        if (StringUtils.isBlank(habilidadDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (checkController.checkIfOwner(personaId)) {
            habilidadDto.setPorcentaje( Math.max(0, Math.min(habilidadDto.getPorcentaje(),100)));//check 0-100
            Habilidad habilidad = new Habilidad(habilidadDto.getNombre(), habilidadDto.getPorcentaje(), personaId);
            habilidadService.save(habilidad);
            return new ResponseEntity(new Mensaje("Habilidad creada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lista/{personaId}")
    public ResponseEntity<List<Habilidad>> listByPersonaId(@PathVariable("personaId") int personaId) {
        List<Habilidad> list = habilidadService.listByPersonaId(personaId);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody HabilidadDto dtohabilidad) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(dtohabilidad.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Habilidad habilidad = habilidadService.getOne(id).get();
        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(habilidad.getPersonaId()))) {
            habilidad.setNombre(dtohabilidad.getNombre());
            habilidad.setPorcentaje(dtohabilidad.getPorcentaje());
            habilidadService.save(habilidad);
            return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }
}
