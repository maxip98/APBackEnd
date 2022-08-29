package com.portfolio.maxi.Controller;

import com.portfolio.maxi.Entity.Persona;
import com.portfolio.maxi.Interface.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired
    IPersonaService iPersonaService;

    @GetMapping("personas/traer")
    public List<Persona> getPersona() {
        return iPersonaService.getPersona();
    }

    @PostMapping("personas/crear")
    public String createPersona(@RequestBody Persona persona){
        iPersonaService.savePersona(persona);
        return "La persona fue creada correctamente";
    }

    @PostAuthorize("hasRole('ADMIN')")
    @DeleteMapping("personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        iPersonaService.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }

    @PutMapping("personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam("nombre") String nuevoNombre,
                               @RequestParam("apellido") String nuevoApellido,
                               @RequestParam("descripcion") String nuevoDescripcion,
                               @RequestParam("img") String nuevoimg){
        Persona persona = iPersonaService.findPersona(id);

        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setDescripcion(nuevoDescripcion);
        persona.setImg(nuevoimg);

        iPersonaService.savePersona(persona);
        return persona;
    }

    @GetMapping("personas/traer/perfil")
    public Persona findPersona(){
        return iPersonaService.findPersona((long)1);
    }


}
