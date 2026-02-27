package co.edu.corhuila.service_Login.Services;


import co.edu.corhuila.service_Login.Domain.Entities.Bitacora;
import co.edu.corhuila.service_Login.Domain.Entities.Usuario;
import co.edu.corhuila.service_Login.Domain.Enums.EstadoUsuario;
import co.edu.corhuila.service_Login.Repositories.BitacoraRepository;
import co.edu.corhuila.service_Login.Repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BitacoraRepository bitacoraRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public AuthService(UsuarioRepository usuarioRepository,
                       BitacoraRepository bitacoraRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bitacoraRepository = bitacoraRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public  Usuario login(String email, String password) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        if (usuario.getEstado() != EstadoUsuario.ACTIVO) {
            throw new RuntimeException("Usuario no activo");
        }

        return usuario;
    }


}
