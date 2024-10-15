
package com.ntt.microservicioclientes.repositorio;

import com.ntt.microservicioclientes.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ElySanchez
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
