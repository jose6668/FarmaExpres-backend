# FarmaExpres-backend

## Diagrama de clases 

- Siguiendo los lineamientos de los Requerimientos se realiza el Diagrama de Clases para el SOftware de FarmaExpres.
[Requerimientos](https://github.com/JerssonF/Week-3.git)

### Creacion de las Clases

#### *Creacion de Clases de autenticación*

- **Clase Usuario**
- Gestiona autenticación y control de acceso.
```mermaid
classDiagram
class Usuario {
  +ID id
  +String nombre
  +String email
  +String password
  +EstadoUsuario estado
  +rol Rol
  +autenticar()
  +cambiarPassword()
  +registrarIntentoFallido()
  +bloquearUsuario()
}
```

- **Clase Rol** 
- Define permisos del usuario.

```mermaid
classDiagram
class Rol {
  +ID id
  +String nombre
  +List~String~ permisos
}

```

- **Clase Sesion** 
- Controla acceso e inactividad.

```mermaid
classDiagram
class Sesion {
  +ID id
  +DateTime fechaInicio
  +DateTime fechaExpiracion
  +boolean activa
  +cerrarSesion()
  +validarInactividad()
}

```

- **Clase Bitacora** 
- Registra acciones críticas del sistema.

```mermaid
classDiagram
class Bitacora {
  +ID id
  +Usuario usuario
  +String accion
  +DateTime fechaHora
}

```

#### *Creacion de Clases de Inventario*

- **Clase de Medicamento**
- Entidad principal del catálogo.

```mermaid
classDiagram
class Medicamento {
  +ID id
  +String codigo
  +String nombre
  +String descripcion
  +EstadoMedicamento estado
  +Float valorunitario
  +actualizarDatos()
  +estaBajoStock()
}

```

- **Clase Lote**
- Permite trazabilidad por número y vencimiento.

```mermaid
classDiagram
class Lote {
  +ID id
  +String numeroLote
  +DateTime fechaVencimiento
  +Medicamento medicamento
  +int stockActual
  +EstadoLote estado
  +int version
  +descontarStock()
  +aumentarStock()
  +estaVencimiento()
}


```

#### *Creacion de Clases de Movimientos*

- **Clase Movimiento**

```mermaid
classDiagram
class Movimiento {
  <<abstract>>
  +ID id
  +DateTime fechaHora
  +Usuario usuario
  +Lote lote
  +int cantidad
  +TipoMovimiento tipo
  +ejecutar()
}

```

- **Clase HistorialMovimiento**
- Permite Guardar toda la información de los movimientos.
```mermaid
classDiagram
class HistorialMovimiento {
  +ID id
  +Movimiento movimiento
  +DateTime fechamovimiento
}

```

#### *Creacion de pagos*
- **Case venta**
- Representa la operación comercial.

```mermaid
classDiagram
class Venta {
  +ID id
  +Float total
  +Usuario usuario
  +Medicamento medicamento
  +Lote lote
  +metodo metodopago
  +calcularTotal()
  +confirmarVenta()
  +cancelarVenta()
}

```


#### *Servicios de Aplicación*

- **InventarioService**

```mermaid
classDiagram
class InventarioService {
  +registrarMedicamento()
  +actualizarMedicamento()
  +eliminarMedicamento()
  +consultarInventario()
}

```

- **AlertService** 

```mermaid
classDiagram
class AlertService {
  +Vencimiento()
  +Alerta()
  +bloquearLoteVencido()
}
```
### Diagrama de Clases 

```mermaid
classDiagram


%% =====================
%% SEGURIDAD
%% =====================

class Usuario {
  +ID id
  +String nombre
  +String email
  +String password
  +EstadoUsuario estado
  +rol Rol
  +autenticar()
  +cambiarPassword()
  +registrarIntentoFallido()
  +bloquearUsuario()
}

class Rol {
  +UUID id
  +String nombre
  +List~String~ permisos
}

class Sesion {
  +ID id
  +DateTime fechaInicio
  +DateTime fechaExpiracion
  +boolean activa
  +cerrarSesion()
  +validarInactividad()
}

class Bitacora {
  +ID id
  +Usuario usuario
  +String accion
  +DateTime fechaHora
}

Usuario "1" --> "1" Rol
Usuario "1" --> "*" Sesion
Usuario "1" --> "*" Bitacora

%% =====================
%% INVENTARIO
%% =====================

class Medicamento {
  +ID id
  +String codigo
  +String nombre
  +String descripcion
  +EstadoMedicamento estado
  +Float valorunitario
  +actualizarDatos()
  +estaBajoStock()
}

class Lote {
  +ID id
  +String numeroLote
  +DateTime fechaVencimiento
  +Medicamento medicamento
  +int stockActual
  +EstadoLote estado
  +int version
  +descontarStock()
  +aumentarStock()
  +estaVencimiento()
}

Lote "1" --> "*" Medicamento

%% =====================
%% MOVIMIENTOS
%% =====================

class Movimiento {
  <<abstract>>
  +ID id
  +DateTime fechaHora
  +Usuario usuario
  +Lote lote
  +int cantidad
  +TipoMovimiento tipo
  +ejecutar()
}

class HistorialMovimiento {
  +UUID id
  +Movimiento movimiento
  +Datetime fechamovimiento
}


Movimiento "*" --> "1" Usuario
Movimiento "*" --> "1" Lote
Movimiento "1" --> "1" HistorialMovimiento

%% =====================
%% PAGOS
%% =====================

class Venta {
  +ID id
  +Float total
  +Usuario usuario
  +Medicamento medicamento
  +Lote lote
  +metodo metodopago
  +calcularTotal()
  +confirmarVenta()
  +cancelarVenta()
}

Venta "1" --> "1" Usuario 
Venta "1" --> "*" Lote
Venta "1" --> "*" Medicamento

%% =====================
%% SERVICIOS
%% =====================

class InventarioService {
  +registrarMedicamento()
  +actualizarMedicamento()
  +eliminarMedicamento()
  +consultarInventario()
}

class AlertService {
  +Vencimiento()
  +Alerta()
  +bloquearLoteVencido()
}
InventarioService ..> Medicamento
InventarioService ..> Lote
AlertService ..> Lote


```

