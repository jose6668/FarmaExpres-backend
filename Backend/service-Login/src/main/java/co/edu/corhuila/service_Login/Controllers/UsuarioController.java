package co.edu.corhuila.service_Login.Controllers;


import co.edu.corhuila.service_Login.Domain.Entities.Usuario;
import co.edu.corhuila.service_Login.Services.UsuarioService;
import co.edu.corhuila.service_Login.dto.CambiarPasswordRequest;
import co.edu.corhuila.service_Login.dto.UsuarioRequest;
import co.edu.corhuila.service_Login.dto.UsuarioResponse;
import org.springframework.http.ResponseEntity;
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


    @PutMapping("/{id}/password")
    public ResponseEntity<String> cambiarPassword(
            @PathVariable Long id,
            @RequestBody CambiarPasswordRequest request) {

        usuarioService.cambiarPassword(
                id,
                request.getPasswordActual(),
                request.getNuevaPassword()
        );

        return ResponseEntity.ok("Contraseña actualizada correctamente");
    }


    @PutMapping("/{id}/bloquear")
    public ResponseEntity<String> bloquearUsuario(@PathVariable Long id) {

        usuarioService.bloquearUsuario(id);

        return ResponseEntity.ok("Usuario bloqueado correctamente");
    }

    @PutMapping("/{id}/desbloquear")
    public ResponseEntity<String> desbloquearUsuario(@PathVariable Long id) {

        usuarioService.desbloquearUsuario(id);

        return ResponseEntity.ok("Usuario desbloqueado correctamente");
    }
}
