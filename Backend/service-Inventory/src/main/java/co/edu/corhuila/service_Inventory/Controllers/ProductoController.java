package co.edu.corhuila.service_Inventory.Controllers;


import co.edu.corhuila.service_Inventory.Domain.Entities.Producto;
import co.edu.corhuila.service_Inventory.Services.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @GetMapping
    public List<Producto> listar() {
        return productoService.listarProductos();
    }
}
