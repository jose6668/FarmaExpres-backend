package co.edu.corhuila.service_Inventory.Repositories;

import co.edu.corhuila.service_Inventory.Domain.Entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoteRepository extends JpaRepository<Lote, Long> {

    List<Lote> findByProductoId(Long productoId);
}
