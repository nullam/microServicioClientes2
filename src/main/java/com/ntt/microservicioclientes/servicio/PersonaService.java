package com.ntt.microservicioclientes.servicio;

import com.ntt.microservicioclientes.entidad.Persona;
import com.ntt.microservicioclientes.repositorio.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ElySanchez
 */
@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    //Crear persona
    public Persona addPersona(String nombre, String genero, Integer edad, String identificacion, String tipoIdentificacion, String direccion, Integer telefono) {
        Persona persona = new Persona(nombre, genero, edad, identificacion, tipoIdentificacion, direccion, telefono);
        return personaRepository.save(persona);
    }

    public void updatePersona(Persona persona) {
        personaRepository.save(persona);
    }

    public void deletePersona(Integer id) {
        personaRepository.deleteById(id);
    }

    public Persona getPersonaById(Integer id) {
        return personaRepository.findById(id).orElse(null);
    }

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }
}
