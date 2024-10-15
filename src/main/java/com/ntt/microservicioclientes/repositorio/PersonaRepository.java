package com.ntt.microservicioclientes.repositorio;

import com.ntt.microservicioclientes.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ElySanchez
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}
