package co.edu.corhuila.service_Inventory.Domain.Entities;


import co.edu.corhuila.service_Inventory.Domain.Enums.TipoMovimiento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
public class Movimiento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipo;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public Movimiento() {}

    public Movimiento(TipoMovimiento tipo,
                      Integer cantidad,
                      Producto producto) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.producto = producto;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Producto getProducto() {
        return producto;
    }
}
