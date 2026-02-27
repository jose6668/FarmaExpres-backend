
package co.edu.corhuila.service_Login.Domain.Entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bitacora")
public class Bitacora {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private String accion;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    public Bitacora() {}

    public Bitacora(Long usuarioId, String accion) {
        this.usuarioId = usuarioId;
        this.accion = accion;
        this.fechaHora = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getAccion() {
        return accion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}
