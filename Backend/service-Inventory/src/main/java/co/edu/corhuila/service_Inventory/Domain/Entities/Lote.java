package co.edu.corhuila.service_Inventory.Domain.Entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotes")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroLote;

    private Integer cantidad;

    private LocalDate fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public Lote() {}

    public Lote(String numeroLote, Integer cantidad,
                LocalDate fechaVencimiento,
                Producto producto) {
        this.numeroLote = numeroLote;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        this.producto = producto;
    }

    public Long getId() { return id; }


}
