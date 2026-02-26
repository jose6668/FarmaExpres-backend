package co.edu.corhuila.service_Login.Repositories;

import co.edu.corhuila.service_Login.Domain.Entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(String nombre);
}
