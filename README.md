# Taller - Patrones de Diseño de Interfaces de Usuario (UI Patterns) y Ética en UI

Implementación en Java de los patrones de interacción para un sistema universitario de reservas y configuración de laboratorios y equipos, enfocado en buenas prácticas de experiencia de usuario y prevención de patrones oscuros (Dark Patterns).

## Estructura del proyecto

El código fuente se encuentra en la carpeta `src/` organizado en los siguientes paquetes:

- `navigation`: Contiene `BreadcrumbNavigator`, encargado de rastrear y construir la ruta jerárquica de pestañas y niveles de navegación.
- `content`: Contiene `TarjetaElemento`, que implementa Progressive Disclosure para mostrar inicialmente la información esencial de un recurso y desplegar los datos técnicos solo a petición del usuario.
- `input`: Contiene `ValidadorForgiving`, que limpia y normaliza números telefónicos, fechas y correos sin arrojar errores ni bloquear al usuario ante variaciones de formato.
- `state`: Contiene `GestorSesionUI`, que maneja el estado del formulario con el Completeness Meter, almacena borradores temporales mediante Lazy Registration, emite notificaciones Toast y gestiona acciones neutras de cancelación y cierre de sesión.
- `main`: Contiene `Main`, clase principal que ejecuta los 5 casos de prueba requeridos.

## Compilación y ejecución

Desde la raíz del repositorio:

```bash
# Compilar todas las clases
javac -encoding UTF-8 -d bin src/navigation/*.java src/content/*.java src/input/*.java src/state/*.java src/main/*.java

# Ejecutar el programa principal
java -cp bin main.Main
```

## Salida de los casos de prueba en consola

```text
=================================================
UI PATTERNS & ETHICS SIMULATION
=================================================

--- 1. PATRÓN DE NAVEGACIÓN (BREADCRUMBS + TABS) ---
Ruta actual: Inicio > Catálogo > Servidor Principal

--- 2. PATRÓN DE CONTENIDO Y ESTRUCTURA (PROGRESSIVE DISCLOSURE) ---
[Vista Resumen] Servidor Principal · Estado: Activo

[Usuario solicita ver detalles avanzados...]
[Acordeón Desplegado] IP: 192.168.1.10 | Puerto: 8080 | RAM: 4GB/16GB

--- 3. PATRÓN DE ENTRADA DE DATOS (FORGIVING FORMAT) ---
Entrada del usuario: "+57 (300) 123-4567"
Teléfono normalizado sin error: "3001234567"

Entrada del usuario (Caso 3): "+57 (310) 999-8877"
Teléfono normalizado sin error: "3109998877"

--- 4. PATRÓN DE FEEDBACK Y ESTADO (COMPLETENESS METER) ---
[Progreso: 33%] Campos listos: 1 de 3 (Falta: Teléfono de contacto, Correo de contacto)
[Progreso: 67%] Campos listos: 2 de 3 (Falta: Correo de contacto)
Usuario ingresa correo: "estudiante@udistrital.edu.co"
[Progreso: 100%] ¡Formulario completo!

--- 5. LAZY REGISTRATION + TOAST ---
Usuario intenta guardar sin haber iniciado sesión...
[Lazy Registration] Avance guardado en borrador temporal. Por favor cree su cuenta para finalizar.

Usuario completa registro rápido -> Sesión activa = true
[Toast de Estado] Reserva guardada exitosamente.

--- 6. ÉTICA EN UI (CERO DARK PATTERNS) ---
Usuario cancela la reserva de forma directa (Sin Roach Motel ni Confirmshaming):
[Acción] Reserva cancelada correctamente.
Usuario cierra sesión en un solo paso:
[Acción] Sesión cerrada correctamente.
```

## Reflexión sobre ética y prevención de Dark Patterns

La implementación se diseñó priorizando la autonomía, la claridad y el respeto hacia el tiempo del usuario. En primer lugar, se evitan prácticas de Roach Motel y Confirmshaming al permitir la cancelación de reservas y el cierre de sesión en un único paso directo, sin laberintos de confirmación ni textos manipuladores o culpabilizantes que busquen disuadir al estudiante. En segundo lugar, mediante Progressive Disclosure se mitiga la sobrecarga cognitiva sin recurrir a Misdirection u ocultamiento engañoso: los detalles técnicos están accesibles de inmediato a un clic de distancia de forma honesta. Finalmente, la combinación de Forgiving Format y Lazy Registration previene la fricción innecesaria y la pérdida de trabajo previo; el sistema asume la responsabilidad de procesar y limpiar la entrada del usuario en lugar de culparlo con mensajes de error rígidos, y preserva la información en un borrador temporal para que el registro sea un paso de soporte y no una barrera forzada antes de interactuar.
