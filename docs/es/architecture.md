# Arquitectura del Sistema

---

## Estructura de Clases

| Clase | Propósito |
|-------|-----------|
| `BusNovaTech` | Clase principal y controlador del flujo |
| `ConfiguracionSistema` | Gestión de configuración y persistencia JSON |
| `GestionBuses` | Administración de buses y tipos |
| `ColaPrioridad` | Implementación de cola de prioridad para tiquetes |
| `NodoTiquete` | Estructura de datos para tiquetes |
| `Nodo<T>` | Nodo genérico para estructuras enlazadas |
| `NodoBus` | Nodo específico para lista de buses |
| `Bus` | Entidad de bus con propiedades básicas |
| `PersistenciaCola` | Serialización/deserialización de tiquetes y colas |
| `ModuloAtencionTiquetes` | Gestión del proceso de atención de tiquetes |
| `HistorialAtenciones` | Control y persistencia de atendidos |
| `RegistroAtencion` | Representa un abordaje confirmado |
| `AsignacionColas` | Lógica de asignación de tiquetes a buses |
| `NodoCola` | Nodo para lista de colas de buses |
| `GestorIdPasajero` | Generación de IDs autoincrementales |
| `GrafoRutas` | Grafo ponderado dirigido para rutas de buses |
| `GestionGrafo` | Gestión del módulo de grafos con menú y persistencia |
| `Localidad` | Representa una localidad donde el bus pararía |
| `VerticeGrafo` | Vértice del grafo con adyacentes usando arrays |
| `AristaGrafo` | Arista con peso para el grafo ponderado |
| `ServicioBCCR` | Consumo del Web Service del BCCR |
| `IndicadorEconomico` | Estructura XML de respuesta del BCCR |
| `GestionTipoCambio` | Gestión de consulta de tipo de cambio |

---

## Estructura de Archivos

```
src/main/java/cr/ac/ufidelitas/proyecto/busnovatech/
├── BusNovaTech.java              # Clase principal
├── ConfiguracionSistema.java     # Módulo 1.0 - Configuración
├── GestionBuses.java             # Módulo 1.0 - Gestión de buses
├── ColaPrioridad.java            # Módulo 1.1 - Cola de prioridad
├── NodoTiquete.java              # Módulo 1.1 - Estructura de tiquete
├── Nodo.java                     # Nodo genérico para estructuras
├── NodoBus.java                  # Nodo específico para buses
├── Bus.java                      # Entidad de bus
├── PersistenciaCola.java         # Módulo 1.1/1.3 - Persistencia
├── ModuloAtencionTiquetes.java   # Módulo 1.2 - Atención de tiquetes
├── HistorialAtenciones.java      # Módulo 1.2 - Historial de atendidos
├── RegistroAtencion.java         # Módulo 1.2 - Registro de atención
├── AsignacionColas.java          # Módulo 1.3 - Asignación de tiquetes
├── NodoCola.java                 # Módulo 1.3 - Nodo para colas
├── GestorIdPasajero.java         # Utilidad - IDs autoincrementales
├── GrafoRutas.java               # Módulo 1.4 - Grafo ponderado dirigido
├── GestionGrafo.java             # Módulo 1.4 - Gestión del grafo
├── Localidad.java                # Módulo 1.4 - Localidad del grafo
├── VerticeGrafo.java             # Módulo 1.4 - Vértice del grafo
├── AristaGrafo.java              # Módulo 1.4 - Arista con peso
├── ServicioBCCR.java             # Módulo 1.5 - Web Service BCCR
├── IndicadorEconomico.java       # Módulo 1.5 - Estructura XML BCCR
└── GestionTipoCambio.java        # Módulo 1.5 - Consulta tipo de cambio
```

---

## Archivos de Datos

El sistema genera y utiliza los siguientes archivos en el directorio de ejecución:

| Archivo | Propósito | Formato |
|---------|-----------|---------|
| `config.json` | Configuración del sistema (terminal, buses, usuarios) | JSON |
| `tiquetes.json` | Cola de tiquetes pendientes | JSON |
| `atendidos.json` | Historial de tiquetes atendidos | JSON |
| `colas.txt` | Estado de colas por bus (cantidad de personas) | TXT |
| `grafo.json` | Grafo de rutas entre localidades | JSON |

> Si se elimina `config.json`, el sistema solicitará reconfiguración completa. Los demás archivos se regeneran automáticamente.

---

## Características Técnicas

### Estructuras de Datos Implementadas

- **Lista Enlazada:** Gestión de buses y colas de buses (`NodoBus`, `NodoCola`)
- **Cola de Prioridad:** Organización de tiquetes por tipo de bus (`ColaPrioridad`)
- **Grafo Ponderado Dirigido:** Rutas entre localidades usando arrays (`GrafoRutas`)
- **Nodo Genérico:** `Nodo<T>` reutilizado en `ColaPrioridad` e `HistorialAtenciones`

### Patrones Aplicados

- **Separación de responsabilidades:** Cada clase tiene un único propósito definido
- **Persistencia por serialización:** JSON para configuración, tiquetes y grafo; TXT para colas
- **Interfaz de usuario por diálogos:** Menús interactivos con `JOptionPane`

### Validaciones

- Verificación de configuración existente al iniciar
- Validación de credenciales de usuario en login
- Control de distribución de buses (1 preferencial, 1 directo, resto normales)
- Validación de campos obligatorios en creación de tiquetes

---

## Referencia de Clases y Métodos

### `BusNovaTech`
Punto de entrada de la aplicación. Gestiona la inicialización, login y menú principal.

- `main(String[] args)` — inicialización, login y flujo principal

---

### `ConfiguracionSistema`
Gestión de configuración del sistema y persistencia en `config.json`.

- `existeConfiguracion()` — verifica si existe `config.json`
- `cargarConfiguracion()` — carga configuración desde JSON
- `guardarConfiguracion(ConfiguracionSistema)` — guarda en JSON
- `ejecutarConfiguracionInicial()` — solicita y crea configuración inicial
- `validarLogin(String, String)` — valida credenciales

---

### `GestionBuses`
Administración de la lista enlazada de buses del sistema.

- `GestionBuses(ConfiguracionSistema)` — crea buses desde la configuración
- `mostrarBuses()` — muestra lista de buses con su estado
- `obtenerBusDisponiblePorTipo(String)` — busca bus disponible del tipo indicado

---

### `ColaPrioridad`
Cola de prioridad para tiquetes ordenada por tipo de bus (P:3, D:2, N:1).

- `encolar(NodoTiquete)` — agrega tiquete en posición según prioridad
- `desencolar()` — remueve y retorna el tiquete del frente
- `crearTiquete()` — solicita datos al usuario y genera tiquete con ID automático
- `mostrarCola()` — muestra todos los tiquetes en cola
- `verFrente()` — retorna el tiquete del frente sin removerlo
- `exportarTiquetes()` — convierte cola a array para serialización
- `importarTiquetes(NodoTiquete[])` — reconstruye cola desde array

---

### `NodoTiquete`
Estructura de datos que representa un tiquete.

**Atributos:** `nombre`, `id`, `edad`, `moneda`, `horaCompra`, `horaAbordaje`, `servicio`, `tipoBus`

---

### `PersistenciaCola`
Serialización de tiquetes y gestión del menú principal de tiquetes.

- `serializarCola(ColaPrioridad, String)` — guarda cola en JSON
- `deserializarCola(String)` — carga cola desde JSON
- `gestionarTiquetes(ColaPrioridad, ModuloAtencionTiquetes, AsignacionColas)` — menú de gestión
- `guardarColas(AsignacionColas)` — guarda estado de colas en `colas.txt`
- `cargarColas(AsignacionColas, GestionBuses)` — carga estado desde `colas.txt`

---

### `ModuloAtencionTiquetes`
Gestión completa del proceso de atención de tiquetes (módulo 1.2).

- `atenderDesdeMenu(ColaPrioridad, AsignacionColas)` — atiende tiquete desde menú
- `mostrarHistorial()` — muestra historial de atendidos
- `procesarAtencionDesdeCola(ColaPrioridad, boolean, AsignacionColas)` — procesa atención completa
- `calcularCobro(NodoTiquete)` — calcula monto según tipo de servicio
- `confirmarPago(NodoTiquete, double)` — solicita confirmación de pago
- `registrarAtendido(NodoTiquete, Bus, double)` — registra atención en historial

---

### `HistorialAtenciones`
Historial de tiquetes atendidos usando lista enlazada.

- `agregarRegistro(RegistroAtencion)` — agrega registro y guarda en JSON
- `mostrarHistorial()` — muestra todos los registros
- `cargarHistorial()` — carga desde `atendidos.json`
- `guardarHistorial()` — guarda en `atendidos.json`

---

### `RegistroAtencion`
Estructura de datos para un abordaje completado. Contiene pasajero, bus, terminal, hora y monto.

---

### `AsignacionColas`
Asignación de tiquetes a buses y gestión del estado de colas (módulo 1.3).

- `asignarTiquete(String)` — asigna tiquete a bus según tipo y reglas
- `decrementarCola(String)` — decrementa cola al atender tiquete
- `obtenerEstadoColas()` — retorna estado actual de todas las colas
- `agregarBus(Bus, int)` — agrega bus con cantidad inicial
- `actualizarCantidadBus(String, int)` — actualiza cantidad de un bus

---

### `GestorIdPasajero`
Genera IDs autoincrementales consultando el máximo existente en `tiquetes.json` y `atendidos.json`.

- `obtenerSiguienteId()` — retorna el siguiente ID disponible
- `obtenerMaxIdDesdeTiquetes()` — máximo ID en `tiquetes.json`
- `obtenerMaxIdDesdeAtendidos()` — máximo ID en `atendidos.json`

---

### `GrafoRutas`
Grafo ponderado dirigido para rutas entre localidades, implementado con arrays.

- `agregarVertice(Localidad)` — agrega localidad al grafo
- `agregarArista(Localidad, Localidad, int)` — agrega ruta dirigida con peso
- `imprimir()` — representación en texto del grafo
- `rutaMasCorta(Localidad, Localidad)` — ruta más corta con Dijkstra
- `obtenerVertices()` — retorna array de vértices para serialización
- `cargarVertices(VerticeGrafo[])` — carga vértices desde array

---

### `GestionGrafo`
Gestión del módulo 1.4: menú, persistencia y consultas del grafo.

- `gestionarGrafo()` — menú principal del módulo
- `guardarGrafo()` — guarda en `grafo.json`
- `cargarGrafo()` — carga desde `grafo.json`
- `agregarLocalidad()` — agrega localidad desde el menú
- `agregarRuta()` — agrega ruta desde el menú
- `consultarRutaMasCorta()` — consulta ruta más corta entre dos localidades

---

### `ServicioBCCR`
Consumo del Web Service del Banco Central de Costa Rica.

- `obtenerIndicador(...)` — obtiene indicador económico del BCCR via HTTP POST
- `obtenerTipoCambioVenta()` — obtiene tipo de cambio de venta (indicador 318)

---

### `GestionTipoCambio`
Interfaz de usuario para la consulta del tipo de cambio.

- `consultarTipoCambio()` — consulta y muestra el tipo de cambio via JOptionPane
- `extraerTipoCambio(IndicadorEconomico)` — extrae valor desde la respuesta XML

---

## Notas de Desarrollo

- Solo se usan librerías básicas de Java y Gson, sin frameworks externos
- Todas las estructuras de datos (listas enlazadas, colas, grafos) están implementadas desde cero
- La atención de tiquetes es manual mediante la opción "Abordar" — no automática al crear
- El grafo usa exclusivamente arrays; ninguna clase del `Collections` framework
- Las credenciales del BCCR deben configurarse antes de usar el módulo 1.5 en producción
