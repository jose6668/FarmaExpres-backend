package co.edu.corhuila.service_Inventory.Controllers;



import co.edu.corhuila.service_Inventory.Domain.Entities.Venta;
import co.edu.corhuila.service_Inventory.Dto.DetalleVentaResponse;
import co.edu.corhuila.service_Inventory.Dto.VentaRequest;
import co.edu.corhuila.service_Inventory.Services.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<Venta> realizarVenta(@RequestBody VentaRequest request) {
        Venta venta = ventaService.realizarVenta(request);
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/detalles")
    public ResponseEntity<List<DetalleVentaResponse>> listarDetalles() {
        return ResponseEntity.ok(ventaService.listarDetalleVentas());
    }
}
