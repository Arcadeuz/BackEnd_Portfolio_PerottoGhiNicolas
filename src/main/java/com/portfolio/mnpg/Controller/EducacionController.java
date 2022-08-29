package com.portfolio.mnpg.Controller;

import com.portfolio.mnpg.Dto.EducacionDto;
import com.portfolio.mnpg.Entity.Educacion;
import com.portfolio.mnpg.Security.Controller.CheckController;
import com.portfolio.mnpg.Security.Controller.Mensaje;
import com.portfolio.mnpg.Service.EducacionService;
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
@RequestMapping("/educacion")
//@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {

    @Autowired
    EducacionService educacionService;
    @Autowired
    CheckController checkController;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = educacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = educacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(educacionService.getOne(id).get().getPersonaId()))) {
            educacionService.delete(id);
            return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crear/{personaId}")
    public ResponseEntity<?> create(@PathVariable("personaId") int personaId, @RequestBody EducacionDto educacionDto) {
        if (StringUtils.isBlank(educacionDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (checkController.checkIfOwner(personaId)) {
            Educacion educacion = new Educacion(educacionDto.getNombre(), educacionDto.getDescripcion(), personaId);
            educacionService.save(educacion);
            return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lista/{personaId}")
    public ResponseEntity<List<Educacion>> listByPersonaId(@PathVariable("personaId") int personaId) {
        List<Educacion> list = educacionService.listByPersonaId(personaId);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EducacionDto dtoeducacion) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(dtoeducacion.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = educacionService.getOne(id).get();
        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(educacion.getPersonaId()))) {
            educacion.setNombre(dtoeducacion.getNombre());
            educacion.setDescripcion(dtoeducacion.getDescripcion());
            educacionService.save(educacion);
            return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);

        }
    }
}
