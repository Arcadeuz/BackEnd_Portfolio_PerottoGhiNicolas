package com.portfolio.mnpg.Security.Controller;

import com.portfolio.mnpg.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nico
 */
@RestController
public class CheckController {

    @Autowired
    PersonaService personaService;
    
    private UserDetails getUserDetails(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    public String getUsername() {
        return getUserDetails().getUsername();
    }

    public String getUsernameFromPersonaId(int personaId) {
        return personaService.getOne(personaId).get().getPropietario();
    }

    public boolean checkIfOwner(int personaId) {
        return getUsernameFromPersonaId(personaId).equals(getUsername());
    }

    public boolean checkIfAdmin() {
        return getUserDetails().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}
