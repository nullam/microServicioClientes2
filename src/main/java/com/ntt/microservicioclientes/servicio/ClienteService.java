package com.ntt.microservicioclientes.servicio;

import com.ntt.microservicioclientes.DTO.ClienteDTO;
import com.ntt.microservicioclientes.entidad.Cliente;
import com.ntt.microservicioclientes.entidad.Persona;
import com.ntt.microservicioclientes.repositorio.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ElySanchez
 */
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    @Autowired
    private PersonaService personaServicio;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente addCliente(ClienteDTO clienteDTO) {
        //crear y guardar una persona
        Persona persona = personaServicio.addPersona(clienteDTO.getNombre(), clienteDTO.getGenero(), clienteDTO.getEdad(), clienteDTO.getIdentificacion(), clienteDTO.getTipoIdentificacion(), clienteDTO.getDireccion(), clienteDTO.getTelefono());

        //crea y guarda un cliente
        Cliente cliente = new Cliente(clienteDTO.getUsuario(), clienteDTO.getPassword(), clienteDTO.getEstado(), persona);
        return clienteRepository.save(cliente);

    }

    @Transactional
    public Cliente updateCliente(Integer id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Persona persona = clienteExistente.getPersona();
        persona.setNombre(clienteDTO.getNombre());
        persona.setDireccion(clienteDTO.getDireccion());
        persona.setEdad(clienteDTO.getEdad());
        persona.setGenero(clienteDTO.getGenero());
        persona.setIdentificacion(clienteDTO.getIdentificacion());
        persona.setTelefono(clienteDTO.getTelefono());

        personaServicio.updatePersona(persona);

        clienteExistente.setPassword(clienteDTO.getPassword());
        clienteExistente.setEstado(clienteDTO.getEstado());
        return clienteRepository.save(clienteExistente);
    }

    @Transactional
    public boolean deleteCliente(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
        return true;
    }

    public Optional<Cliente> getClienteById(Integer id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
}
