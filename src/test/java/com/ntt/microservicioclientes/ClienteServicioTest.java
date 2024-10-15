package com.ntt.microservicioclientes;

import com.ntt.microservicioclientes.DTO.ClienteDTO;
import com.ntt.microservicioclientes.entidad.Cliente;
import com.ntt.microservicioclientes.servicio.ClienteService;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author ElySanchez
 */
@SpringBootTest
@ActiveProfiles("test")
public class ClienteServicioTest {
    
    @Autowired
    private ClienteService clienteServicio;
    
   @Test
    public void registrarClienteCorrectamente() {
        ClienteDTO clienteDTO = new ClienteDTO("Usuario","13123", "ACTIVO", "Nombre", "Femenino", 35, "1716868656", "cedula", "pomasqui", Integer.valueOf("0984509857"));
        Cliente guardado = clienteServicio.addCliente(clienteDTO);
        Optional<Cliente> encontrado = clienteServicio.getClienteById(guardado.getIdCliente());

        assertTrue(encontrado.isPresent());
        assertEquals("nombre", encontrado.get().getPersona().getNombre());
    }
    
}
