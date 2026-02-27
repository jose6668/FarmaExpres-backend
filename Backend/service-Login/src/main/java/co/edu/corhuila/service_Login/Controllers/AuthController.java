package co.edu.corhuila.service_Login.Controllers;


import co.edu.corhuila.service_Login.Domain.Entities.Usuario;
import co.edu.corhuila.service_Login.Services.AuthService;
import co.edu.corhuila.service_Login.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        return token;
    }



}
