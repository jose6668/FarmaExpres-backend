package co.edu.corhuila.service_Login.Repositories;

import co.edu.corhuila.service_Login.Domain.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

}
