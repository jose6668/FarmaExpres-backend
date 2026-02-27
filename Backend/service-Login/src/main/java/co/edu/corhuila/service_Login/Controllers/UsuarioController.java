package co.edu.corhuila.service_Login.Controllers;


import co.edu.corhuila.service_Login.Domain.Entities.Usuario;
import co.edu.corhuila.service_Login.Services.UsuarioService;
import co.edu.corhuila.service_Login.dto.UsuarioRequest;
import co.edu.corhuila.service_Login.dto.UsuarioResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioResponse crearUsuario(@RequestBody UsuarioRequest request) {

        Usuario usuario = usuarioService.crearUsuario(
                request.getNombre(),
                request.getEmail(),
                request.getPassword(),
                request.getRol()
        );

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().getNombre()
        );
    }
}
