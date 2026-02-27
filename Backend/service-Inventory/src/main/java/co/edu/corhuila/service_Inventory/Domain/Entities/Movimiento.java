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
    private TipoMovimiento tipo;

    private Integer cantidad;

    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "producto_id")
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

    public Long getId() { return id; }

}


