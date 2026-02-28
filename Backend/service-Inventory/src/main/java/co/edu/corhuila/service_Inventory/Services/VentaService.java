package co.edu.corhuila.service_Inventory.Services;

import co.edu.corhuila.service_Inventory.Domain.Entities.DetalleVenta;
import co.edu.corhuila.service_Inventory.Domain.Entities.Movimiento;
import co.edu.corhuila.service_Inventory.Domain.Entities.Producto;
import co.edu.corhuila.service_Inventory.Domain.Entities.Venta;
import co.edu.corhuila.service_Inventory.Domain.Enums.TipoMovimiento;
import co.edu.corhuila.service_Inventory.Dto.ItemVenta;
import co.edu.corhuila.service_Inventory.Dto.VentaRequest;
import co.edu.corhuila.service_Inventory.Repositories.MovimientoRepository;
import co.edu.corhuila.service_Inventory.Repositories.ProductoRepository;
import co.edu.corhuila.service_Inventory.Repositories.VentaRepository;
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

    public VentaService(ProductoRepository productoRepository,
                        VentaRepository ventaRepository,
                        MovimientoRepository movimientoRepository) {
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public Venta realizarVenta(VentaRequest request) {

        String usuarioEmail = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        BigDecimal total = BigDecimal.ZERO;
        List<DetalleVenta> detalles = new ArrayList<>();

        Venta venta = new Venta(BigDecimal.ZERO, usuarioEmail);

        for (ItemVenta item : request.getItems()) {

            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (!producto.getActivo()) {
                throw new RuntimeException("Producto inactivo");
            }

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

        venta = new Venta(total, usuarioEmail);
        ventaRepository.save(venta);

        return venta;
    }

}
