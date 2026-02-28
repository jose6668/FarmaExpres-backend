package co.edu.corhuila.service_Inventory.Domain.Entities;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private Boolean activo;

    @OneToMany(mappedBy = "producto")
    private List<Lote> lotes;

    public Producto() {}

    public Producto(String nombre, String codigo, Integer stock, BigDecimal precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.stock = stock;
        this.precio = precio;
        this.activo = true;
    }

    public void aumentarStock(Integer cantidad) {
        this.stock += cantidad;
    }

    public void disminuirStock(Integer cantidad) {
        if (this.stock < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        this.stock -= cantidad;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    public Integer getStock() { return stock; }
    public BigDecimal getPrecio() { return precio; }
    public Boolean getActivo() { return activo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
