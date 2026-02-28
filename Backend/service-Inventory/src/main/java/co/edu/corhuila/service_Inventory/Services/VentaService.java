package co.edu.corhuila.service_Inventory.Services;

import co.edu.corhuila.service_Inventory.Domain.Entities.DetalleVenta;
import co.edu.corhuila.service_Inventory.Domain.Entities.Movimiento;
import co.edu.corhuila.service_Inventory.Domain.Entities.Producto;
import co.edu.corhuila.service_Inventory.Domain.Entities.Venta;
import co.edu.corhuila.service_Inventory.Domain.Enums.TipoMovimiento;
import co.edu.corhuila.service_Inventory.Dto.DetalleVentaResponse;
import co.edu.corhuila.service_Inventory.Dto.ItemVenta;
import co.edu.corhuila.service_Inventory.Dto.VentaRequest;
import co.edu.corhuila.service_Inventory.Repositories.DetalleVentaRepository;
import co.edu.corhuila.service_Inventory.Repositories.MovimientoRepository;
import co.edu.corhuila.service_Inventory.Repositories.ProductoRepository;
import co.edu.corhuila.service_Inventory.Repositories.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {

    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final MovimientoRepository movimientoRepository;
    private final DetalleVentaRepository detalleVentaRepository;


    public VentaService(ProductoRepository productoRepository,
                        VentaRepository ventaRepository,
                        MovimientoRepository movimientoRepository,
                        DetalleVentaRepository detalleVentaRepository
                        ) {
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
        this.movimientoRepository = movimientoRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Transactional
    public Venta realizarVenta(VentaRequest request) {
        String usuarioEmail = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        BigDecimal total = BigDecimal.ZERO;

        Venta venta = new Venta(BigDecimal.ZERO, usuarioEmail);

        List<DetalleVenta> detalles = new ArrayList<>();

        for (ItemVenta item : request.getItems()) {

            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            producto.disminuirStock(item.getCantidad());

            BigDecimal subtotal = producto.getPrecio()
                    .multiply(BigDecimal.valueOf(item.getCantidad()));

            total = total.add(subtotal);

            DetalleVenta detalle = new DetalleVenta(
                    item.getCantidad(),
                    producto.getPrecio(),
                    producto,
                    venta
            );

            detalles.add(detalle);

            Movimiento movimiento = new Movimiento(
                    TipoMovimiento.SALIDA,
                    item.getCantidad(),
                    producto
            );

            movimientoRepository.save(movimiento);
        }

        venta.setTotal(total);
        venta.setDetalles(detalles);

        return ventaRepository.save(venta);
    }

    public List<DetalleVentaResponse> listarDetalleVentas() {

        return detalleVentaRepository.findAll()
                .stream()
                .map(DetalleVentaResponse::new)
                .toList();
    }

}
