package com.portfolio.mnpg.Controller;

import com.portfolio.mnpg.Dto.ProyectoDto;
import com.portfolio.mnpg.Entity.Proyecto;
import com.portfolio.mnpg.Security.Controller.CheckController;
import com.portfolio.mnpg.Security.Controller.Mensaje;
import com.portfolio.mnpg.Service.ProyectoService;
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
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;
    @Autowired
    CheckController checkController;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = proyectoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Proyecto experiencia = proyectoService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(proyectoService.getOne(id).get().getPersonaId()))) {
            proyectoService.delete(id);
            return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/crear/{personaId}")
    public ResponseEntity<?> create(@PathVariable("personaId") int personaId, @RequestBody ProyectoDto proyectoDto) {

        if (StringUtils.isBlank(proyectoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (checkController.checkIfOwner(personaId)) {
            Proyecto proyecto = new Proyecto(proyectoDto.getNombre(), proyectoDto.getDescripcion(),
                    proyectoDto.getFotoProyecto(), proyectoDto.getFechaInicio(), proyectoDto.getFechaFin(), personaId);
            proyectoService.save(proyecto);

            return new ResponseEntity(new Mensaje("Proyecto agregada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lista/{personaId}")
    public ResponseEntity<List<Proyecto>> listByPersonaId(@PathVariable("personaId") int personaId) {
        List<Proyecto> list = proyectoService.listByPersonaId(personaId);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProyectoDto proyectoDto) {
        System.out.println("entro");  
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        //No puede estar vacio
        if (StringUtils.isBlank(proyectoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = proyectoService.getOne(id).get();
        if ((checkController.checkIfAdmin() || checkController.checkIfOwner(proyecto.getPersonaId()))) {
            proyecto.setNombre(proyectoDto.getNombre());
            proyecto.setDescripcion(proyectoDto.getDescripcion());
            proyecto.setFotoProyecto(proyectoDto.getFotoProyecto());
            proyecto.setFechaInicio(proyectoDto.getFechaInicio());
            proyecto.setFechaFin(proyectoDto.getFechaFin());
            proyectoService.save(proyecto);
            return new ResponseEntity(new Mensaje("Proyecto actualizada"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("El hacking es malo"), HttpStatus.BAD_REQUEST);
        }
    }
}
