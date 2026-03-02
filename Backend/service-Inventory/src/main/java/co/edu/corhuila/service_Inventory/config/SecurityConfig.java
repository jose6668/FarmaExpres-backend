package co.edu.corhuila.service_Inventory.config;


import co.edu.corhuila.service_Inventory.Services.JwtFilter;
import co.edu.corhuila.service_Inventory.Services.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // EMPLEADO puede CREAR ventas
                        .requestMatchers(HttpMethod.POST, "/ventas/**")
                        .hasRole("EMPLEADO")

                        // ADMIN puede ver detalleventas
                        .requestMatchers(HttpMethod.GET, "/ventas/detalles")
                        .hasRole("ADMIN")

                        // ADMIN puede gestionar productos (crear, actualizar, eliminar y ver)
                        .requestMatchers(HttpMethod.POST, "/productos/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/productos/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/productos/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/productos/**")
                        .hasRole("ADMIN")


                        // ADMIN puede ver Movimientos
                        .requestMatchers(HttpMethod.GET, "/movimientos/**")
                        .hasRole("ADMIN")

                        // EMPLEADO puede consultar productos
                        .requestMatchers(HttpMethod.GET, "/productos/**")
                        .hasRole("EMPLEADO")

                        //
                        .requestMatchers(HttpMethod.GET, "/movimientos/**")
                        .hasRole("ADMIN")


                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
