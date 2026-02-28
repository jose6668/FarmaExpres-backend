package co.edu.corhuila.service_Inventory.Domain.Entities;


import co.edu.corhuila.service_Inventory.Domain.Enums.EstadoVenta;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private EstadoVenta estado;

    private BigDecimal total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    @Column(nullable = false)
    private String usuarioEmail;



    public Venta() {}

    public Venta(BigDecimal total, String usuarioEmail) {
        this.fecha = LocalDateTime.now();
        this.estado = EstadoVenta.COMPLETADA;
        this.total = total;
        this.usuarioEmail = usuarioEmail;
    }



    public Long getId() { return id; }
    public BigDecimal getTotal() { return total; }

    public String getUsuarioEmail() { return usuarioEmail; }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}
