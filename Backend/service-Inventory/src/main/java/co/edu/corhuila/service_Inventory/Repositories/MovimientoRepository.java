package co.edu.corhuila.service_Inventory.Repositories;

import co.edu.corhuila.service_Inventory.Domain.Entities.Movimiento;
import co.edu.corhuila.service_Inventory.Domain.Enums.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByProductoId(Long productoId);

    List<Movimiento> findByTipo(TipoMovimiento tipo);

}
