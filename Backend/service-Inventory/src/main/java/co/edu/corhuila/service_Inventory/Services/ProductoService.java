package co.edu.corhuila.service_Inventory.Services;


import co.edu.corhuila.service_Inventory.Domain.Entities.Movimiento;
import co.edu.corhuila.service_Inventory.Domain.Entities.Producto;
import co.edu.corhuila.service_Inventory.Domain.Enums.TipoMovimiento;
import co.edu.corhuila.service_Inventory.Repositories.MovimientoRepository;
import co.edu.corhuila.service_Inventory.Repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {


    private final ProductoRepository productoRepository;
    private final MovimientoRepository movimientoRepository;

    public ProductoService(ProductoRepository productoRepository,
                           MovimientoRepository movimientoRepository) {
        this.productoRepository = productoRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public Producto crearProducto(Producto producto) {

        if (productoRepository.existsByCodigo(producto.getCodigo())) {
            throw new RuntimeException("El código ya existe");
        }

        Producto productoGuardado = productoRepository.save(producto);

        Movimiento movimiento = new Movimiento(
                TipoMovimiento.ENTRADA,
                productoGuardado.getStock(),
                productoGuardado
        );

        movimientoRepository.save(movimiento);

        return productoGuardado;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
