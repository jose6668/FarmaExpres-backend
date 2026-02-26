package co.edu.corhuila.service_Login.Repositories;

import co.edu.corhuila.service_Login.Domain.Entities.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {

    List<Bitacora> findByUsuarioId(Long usuarioId);
}
