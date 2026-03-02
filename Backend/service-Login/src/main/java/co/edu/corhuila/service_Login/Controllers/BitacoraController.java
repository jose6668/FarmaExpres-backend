package co.edu.corhuila.service_Login.Controllers;

import co.edu.corhuila.service_Login.Domain.Entities.Bitacora;
import co.edu.corhuila.service_Login.Repositories.BitacoraRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/bitacora")
public class BitacoraController {

    private final BitacoraRepository bitacoraRepository;

    public BitacoraController(BitacoraRepository bitacoraRepository) {
        this.bitacoraRepository = bitacoraRepository;
    }

    @GetMapping
    public List<Bitacora> listarBitacora() {
        return bitacoraRepository.findAll();
    }
}
