package co.edu.corhuila.service_Inventory.Services;


import co.edu.corhuila.service_Inventory.Domain.Entities.Producto;
import co.edu.corhuila.service_Inventory.Repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {


    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(Producto producto) {

        if (productoRepository.existsByCodigo(producto.getCodigo())) {
            throw new RuntimeException("El código ya existe");
        }

        return productoRepository.save(producto);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
