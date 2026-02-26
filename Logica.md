# Logica

- Respecto a los lineamientos de los [Requerimientos](https://github.com/JerssonF/Week-3.git)
 se implementa la logica para los microservicios y como estaran divididos cada uno deacuerdo a sus **Responsabilidades**. 

## Microservicio 1 *Login*
- Responsabilidades:
    - Registro de usuarios.
    - Autenticación.
    - Gestión de roles.
    - Bitácora de accesos.
    - Control de sesiones.

## Base de datos propia
### Tablas:
- Usuario
- Rol
- BitacoraAcceso <br>

Este micro servicio cuenta con su propia base de datos permitiendo que no interactue ni conosca nada del MicroServicio **inventario**.


## Microservicio 2 *Inventario*
- Responsabilidades:
    - Gestión de medicamentos.
    - Control por lote.
    - Control de vencimiento.
    - Gestión de stock.
    - Entradas.
    - Salidas.
    - Ventas.
    - Historial.
    - Alertas.

## Base de datos propia
### Tablas:
- Producto
- Lote
- Venta
- DetalleVenta
- MovimientoInventario <br>

Este micro servicio cuenta con su propia base de datos permitiendo que no interactue ni conosca nada del MicroServicio **login**

