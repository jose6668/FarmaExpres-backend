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
    private LocalDate fechaVencimiento;



    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos;

    public Producto() {}

    public Producto(String nombre,
                    String codigo,
                    Integer stock,
                    BigDecimal precio,
                    LocalDate fechaVencimiento) {

        this.nombre = nombre;
        this.codigo = codigo;
        this.stock = stock;
        this.precio = precio;
        this.fechaVencimiento = fechaVencimiento;
    }

    // Métodos de negocio importantes

    public void aumentarStock(Integer cantidad) {
        this.stock += cantidad;
    }

    public void disminuirStock(Integer cantidad) {
        if (this.stock < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        this.stock -= cantidad;
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getStock() {
        return stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
