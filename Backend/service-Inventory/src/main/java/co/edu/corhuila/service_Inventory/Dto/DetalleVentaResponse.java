package co.edu.corhuila.service_Inventory.Dto;


import co.edu.corhuila.service_Inventory.Domain.Entities.DetalleVenta;

import java.math.BigDecimal;

public class DetalleVentaResponse {

    private Long id;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private Long productoId;
    private Long ventaId;

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

    public Long getProductoId() {
        return productoId;
    }

    public Long getVentaId() {
        return ventaId;
    }

    public DetalleVentaResponse(DetalleVenta d) {
        this.id = d.getId();
        this.cantidad = d.getCantidad();
        this.precioUnitario = d.getPrecioUnitario();
        this.subtotal = d.getSubtotal();
        this.productoId = d.getProducto().getId();
        this.ventaId = d.getVenta().getId();
    }
}
