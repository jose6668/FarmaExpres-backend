package co.edu.corhuila.service_Login.Services;


import co.edu.corhuila.service_Login.Domain.Entities.Bitacora;
import co.edu.corhuila.service_Login.Domain.Entities.Rol;
import co.edu.corhuila.service_Login.Domain.Entities.Usuario;
import co.edu.corhuila.service_Login.Domain.Enums.EstadoUsuario;
import co.edu.corhuila.service_Login.Repositories.BitacoraRepository;
import co.edu.corhuila.service_Login.Repositories.RolRepository;
import co.edu.corhuila.service_Login.Repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BitacoraRepository bitacoraRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                         RolRepository rolRepository,
                         BitacoraRepository bitacoraRepository,
                         BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.bitacoraRepository = bitacoraRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // Crear Usuario
    // =========================


    public Usuario crearUsuario(String nombre,
                                String email,
                                String password,
                                String nombreRol) {

        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }

        Rol rol = rolRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        String passwordEncriptado = passwordEncoder.encode(password);

        Usuario usuario = new Usuario(
                nombre,
                email,
                passwordEncriptado,
                rol
        );

        usuario.setEstado(EstadoUsuario.ACTIVO);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        bitacoraRepository.save(
                new Bitacora(usuarioGuardado.getId(), "CREACION_USUARIO")
        );

        return usuarioGuardado;
    }

    // =========================
    // Cambiar Contraseña
    // =========================


    public void cambiarPassword(Long usuarioId,
                                String passwordActual,
                                String nuevaPassword
                                ) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(passwordActual, usuario.getPassword())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);

        bitacoraRepository.save(
                new Bitacora(usuario.getId(), "CAMBIO_PASSWORD")
        );
    }

    // =========================
    // Bloquear Usuario
    // =========================

    public void bloquearUsuario(Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.bloquear();
        usuarioRepository.save(usuario);

        bitacoraRepository.save(
                new Bitacora(usuario.getId(), "USUARIO_BLOQUEADO")
        );
    }

    // =========================
    // Desbloquear Usuario
    // =========================

    public void desbloquearUsuario(Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.desbloquear();
        usuarioRepository.save(usuario);

        bitacoraRepository.save(
                new Bitacora(usuario.getId(), "USUARIO_DESBLOQUEADO")
        );
    }


}
