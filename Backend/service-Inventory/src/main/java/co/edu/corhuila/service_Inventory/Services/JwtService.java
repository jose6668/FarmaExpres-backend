package co.edu.corhuila.service_Inventory.Services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


@Service
public class JwtService {

    private final String SECRET = "clave_super_secreta_muy_larga_para_hs256_123456";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public Claims extraerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
