package co.edu.corhuila.service_Inventory.Controllers;


import co.edu.corhuila.service_Inventory.Domain.Entities.Lote;
import co.edu.corhuila.service_Inventory.Services.LoteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @PostMapping
    public Lote crear(@RequestBody Lote lote) {
        return loteService.crearLote(lote);
    }
}
