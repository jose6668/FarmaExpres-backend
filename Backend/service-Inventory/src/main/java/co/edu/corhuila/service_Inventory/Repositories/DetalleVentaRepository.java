package co.edu.corhuila.service_Inventory.Repositories;

import co.edu.corhuila.service_Inventory.Domain.Entities.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByVentaId(Long ventaId);

}
