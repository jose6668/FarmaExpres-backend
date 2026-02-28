package co.edu.corhuila.service_Inventory.Domain.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonIgnore
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

    public Long getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public Venta getVenta() {
        return venta;
    }

    public Producto getProducto() {
        return producto;
    }
}
