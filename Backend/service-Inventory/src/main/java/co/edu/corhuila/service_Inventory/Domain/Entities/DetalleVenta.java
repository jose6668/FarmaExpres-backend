package co.edu.corhuila.service_Inventory.Domain.Entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalleventas")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public DetalleVenta() {}

    public DetalleVenta(Integer cantidad,
                        BigDecimal precioUnitario,
                        Producto producto,
                        Venta venta) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
        this.producto = producto;
        this.venta = venta;
    }

    public Long getId() { return id; }
}
