package co.edu.corhuila.service_Inventory.Services;

import co.edu.corhuila.service_Inventory.Domain.Entities.Lote;
import co.edu.corhuila.service_Inventory.Repositories.LoteRepository;
import org.springframework.stereotype.Service;

@Service
public class LoteService {

    private final LoteRepository loteRepository;

    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Lote crearLote(Lote lote) {
        return loteRepository.save(lote);
    }

}
