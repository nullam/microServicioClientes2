package com.ntt.microservicioclientes;

import com.ntt.microservicioclientes.DTO.ClienteDTO;
import com.ntt.microservicioclientes.controlador.ClienteController;
import com.ntt.microservicioclientes.entidad.Cliente;
import com.ntt.microservicioclientes.entidad.Persona;
import com.ntt.microservicioclientes.servicio.ClienteService;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 *
 * @author ElySanchez
 */
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void testObtenerClienteId() throws Exception {
        Persona mockPersona = new Persona("Jose Lema", "Masculino", 35, "1258748956", "CEDULA", "Otavalo SN y Principal", Integer.valueOf("098254785"));
        
        Cliente mockCliente = new Cliente("jlema", "1234", "TRUE", mockPersona);
        when(clienteService.getClienteById(1)).thenReturn(Optional.of(mockCliente));

        mockMvc.perform(get("/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario").value("jlema"));

        verify(clienteService).getClienteById(1);
    }

    @Test
//    @Disabled
    public void creacionClienteCorrecta() throws Exception {
        //Persona mockPersona = new Persona("Jose Lema", "Masculino", 35, "1258748956", "CEDULA", "Otavalo SN y Principal", Integer.valueOf("098254785"));
        
        ClienteDTO mockCliente = new ClienteDTO("jlema", "1234", "TRUE", "Jose Lema", "Masculino", 35, "1258748956", "CEDULA", "Otavalo SN y Principal", Integer.valueOf("098254785"));
        
        when(clienteService.addCliente(mockCliente));

        String autoJson = "{"
                + "\"placa\":\"ABS-4321\","
                + "\"marca\":\"asdasd\","
                + "\"modelo\":\"asdad\","
                + "\"chasis\":\"asdad\","
                + "\"color\":\"asdad\","
                + "\"estado\":\"true\","
                + "\"idUsuario\":1"
                + "}";

        mockMvc.perform(post("/auto")
                .contentType("application/json")
                .content(autoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.marca").value("asdasd"));

        verify(clienteService).addCliente(mockCliente);
    }

}
