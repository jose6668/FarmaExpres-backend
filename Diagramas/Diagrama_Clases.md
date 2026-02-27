# Diagrama de Clases

## MicroServicio **Login**

### Clases 

- **Clase Usuario**
```mermaid
classDiagram
class Usuario {
  +ID id
  +String nombre
  +String email
  +String password
  +EstadoUsuario estado
  +IDrol Rol
  +autenticar()
  +cambiarPassword()
  +bloquearUsuario()
  +asignarRol(rol)
  +removerRol(rol)
}
```
- **Clase Rol**
```mermaid
classDiagram
class Rol {
  +ID id
  +String nombre
  +String descripcion
  +boolean activo
  +asignarPermiso()
  +removerPermiso()
}
```
- **Clase Bitacora**

```mermaid
classDiagram
class Bitacora {
  +ID id
  +Usuario usuario
  +String accion
  +DateTime fechaHora
  +registrarAcceso(usuario, accion)
}
```

## MicroServicio **Inventario**

### Clases 

- **Clase Producto**
```mermaid
classDiagram
class Producto {
  +ID id
  +String codigo
  +String nombre
  +Int Stock
  +Float precio
  +Boolean activo
  +stockTotal()
}
```

- **Clase Lote**
```mermaid
classDiagram
class Lote {
  +ID id
  +String numeroLote
  +Producto producto
  +DateTime fechaVencimiento
  +Int catidad
  +descontarStock()
  +aumentarStock()
  +estaVencimiento()
}
```

- **Clase Venta**
```mermaid
classDiagram
class Venta {
  +ID id
  +IDUsuario usuario
  +DateTime fecha
  +EstadoVenta estado
  +Float total
  +confirmarVenta()
  +calcularTotal()
  +anularVenta()
}
```
- **Clase DetalleVenta**
```mermaid
classDiagram
class DetalleVenta {
  +ID id
  +Int cantidad
  +Float preciounitario
  +Lote lote
  +Venta venta
  +Float subtotal
  +IDProducto producto
  +calcularSubtotal()
  +validarCantidadDisponible()
}
```

- **Clase Movimiento**

```mermaid
classDiagram
class Movimiento {
  +ID id
  +Int Cantidad
  +DateTime fecha
  +IDUsuario usuario
  +IDProducto producto
  +TipoMovimineto tipo
  +registrarEntrada()
  +registrarSalida()
}

```
- **Diagrama de Clases**

```mermaid
classDiagram
%% =====================
%% LOGIN
%% =====================

class Usuario {
  +ID id
  +String nombre
  +String email
  +String password
  +EstadoUsuario estado
  +IDrol Rol
  +autenticar()
  +cambiarPassword()
  +bloquearUsuario()
  +asignarRol(rol)
  +removerRol(rol)
}

class Rol {
  +ID id
  +String nombre
  +String descripcion
  +boolean activo
  +asignarPermiso()
  +removerPermiso()
}

class Bitacora {
  +ID id
  +Usuario usuario
  +String accion
  +DateTime fechaHora
  +registrarAcceso(usuario, accion)
}



%% =====================
%% Inventario
%% =====================


class Producto {
  +ID id
  +String codigo
  +String nombre
  +Int Stock
  +Float precio
  +Boolean activo
  +stockTotal()
}

class Lote {
  +ID id
  +String numeroLote
  +Producto producto
  +DateTime fechaVencimiento
  +Int catidad
  +descontarStock()
  +aumentarStock()
  +estaVencimiento()
}

class Venta {
  +ID id
  +IDUsuario usuario
  +DateTime fecha
  +EstadoVenta estado
  +Float total
  +confirmarVenta()
  +calcularTotal()
  +anularVenta()
}

class DetalleVenta {
  +ID id
  +Int cantidad
  +Float preciounitario
  +Lote lote
  +Venta venta
  +Float subtotal
  +IDProducto producto
  +calcularSubtotal()
  +validarCantidadDisponible()
}


class Movimiento {
  +ID id
  +Int Cantidad
  +DateTime fecha
  +IDUsuario usuario
  +IDProducto producto
  +TipoMovimineto tipo
  +registrarEntrada()
  +registrarSalida()
}



Usuario "1" -- "*" Venta
Usuario "1" -- "*" Movimiento
Usuario "1" -- "1" Rol
Usuario "1" -- "*" Bitacora
Producto "1" -- "*" Lote
Venta "1" -- "*" DetalleVenta
Lote "1" -- "*" Movimiento
Lote "1" -- "*" DetalleVenta
```