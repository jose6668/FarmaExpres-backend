package co.edu.corhuila.service_Inventory.Services;


import co.edu.corhuila.service_Inventory.Domain.Entities.Movimiento;
import co.edu.corhuila.service_Inventory.Repositories.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    public MovimientoService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    public List<Movimiento> listarMovimientos() {
        return movimientoRepository.findAll();
    }
}
