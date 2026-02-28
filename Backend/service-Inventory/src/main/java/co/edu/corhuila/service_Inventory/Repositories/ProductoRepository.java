package co.edu.corhuila.service_Inventory.Repositories;

import co.edu.corhuila.service_Inventory.Domain.Entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);
}
