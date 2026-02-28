package co.edu.corhuila.service_Inventory.Controllers;


import co.edu.corhuila.service_Inventory.Domain.Entities.Movimiento;
import co.edu.corhuila.service_Inventory.Services.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> listar() {
        return ResponseEntity.ok(movimientoService.listarMovimientos());
    }
}
