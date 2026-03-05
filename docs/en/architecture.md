# System Architecture

---

## Class Overview

| Class | Purpose |
|---|---|
| `BusNovaTech` | Main class and application flow controller |
| `ConfiguracionSistema` | System configuration management and JSON persistence |
| `GestionBuses` | Bus list administration |
| `ColaPrioridad` | Priority queue implementation for tickets |
| `NodoTiquete` | Ticket data structure |
| `Nodo<T>` | Generic node for linked structures |
| `NodoBus` | Bus-specific linked list node |
| `Bus` | Bus entity with basic properties |
| `PersistenciaCola` | Ticket and queue serialization/deserialization |
| `ModuloAtencionTiquetes` | Full ticket attendance process management |
| `HistorialAtenciones` | Attendance history control and persistence |
| `RegistroAtencion` | Represents a confirmed boarding |
| `AsignacionColas` | Ticket-to-bus assignment logic |
| `NodoCola` | Linked list node for bus queues |
| `GestorIdPasajero` | Auto-incremental ID generation |
| `GrafoRutas` | Weighted directed graph for bus routes |
| `GestionGrafo` | Graph module management: menu and persistence |
| `Localidad` | Represents a bus stop locality |
| `VerticeGrafo` | Graph vertex with array-based adjacency list |
| `AristaGrafo` | Weighted edge for the directed graph |
| `ServicioBCCR` | BCCR Web Service consumer |
| `IndicadorEconomico` | XML response structure from BCCR |
| `GestionTipoCambio` | Exchange rate query management |

---

## File Tree

```
src/main/java/cr/ac/ufidelitas/proyecto/busnovatech/
├── BusNovaTech.java              # Main class
├── ConfiguracionSistema.java     # Module 1.0 - Configuration
├── GestionBuses.java             # Module 1.0 - Bus management
├── ColaPrioridad.java            # Module 1.1 - Priority queue
├── NodoTiquete.java              # Module 1.1 - Ticket structure
├── Nodo.java                     # Generic node for structures
├── NodoBus.java                  # Bus-specific node
├── Bus.java                      # Bus entity
├── PersistenciaCola.java         # Module 1.1/1.3 - Persistence
├── ModuloAtencionTiquetes.java   # Module 1.2 - Ticket attendance
├── HistorialAtenciones.java      # Module 1.2 - Attendance history
├── RegistroAtencion.java         # Module 1.2 - Attendance record
├── AsignacionColas.java          # Module 1.3 - Queue assignment
├── NodoCola.java                 # Module 1.3 - Queue node
├── GestorIdPasajero.java         # Utility - Auto-incremental IDs
├── GrafoRutas.java               # Module 1.4 - Weighted directed graph
├── GestionGrafo.java             # Module 1.4 - Graph management
├── Localidad.java                # Module 1.4 - Graph locality
├── VerticeGrafo.java             # Module 1.4 - Graph vertex
├── AristaGrafo.java              # Module 1.4 - Weighted edge
├── ServicioBCCR.java             # Module 1.5 - BCCR Web Service
├── IndicadorEconomico.java       # Module 1.5 - BCCR XML structure
└── GestionTipoCambio.java        # Module 1.5 - Exchange rate query
```

---

## Data Files

The system generates and uses the following files in the execution directory:

| File | Purpose | Format |
|---|---|---|
| `config.json` | System configuration (terminal, buses, users) | JSON |
| `tiquetes.json` | Pending ticket queue | JSON |
| `atendidos.json` | Attended ticket history | JSON |
| `colas.txt` | Queue state per bus (number of people) | TXT |
| `grafo.json` | Route graph between localities | JSON |

> If `config.json` is deleted, the system will require full reconfiguration. All other files are regenerated automatically.

---

## Technical Details

### Data Structures Implemented

- **Linked List:** Bus management and bus queue tracking (`NodoBus`, `NodoCola`)
- **Priority Queue:** Ticket organization by bus type (`ColaPrioridad`)
- **Weighted Directed Graph:** Routes between localities using arrays (`GrafoRutas`)
- **Generic Node:** `Nodo<T>` reused in `ColaPrioridad` and `HistorialAtenciones`

### Design Patterns Applied

- **Single responsibility:** Each class has one clearly defined purpose
- **Serialization persistence:** JSON for configuration, tickets and graph; TXT for queues
- **Dialog-based UI:** Interactive menus with `JOptionPane`

### Validations

- Configuration existence check on startup
- User credential validation at login
- Bus distribution control (1 priority, 1 direct, rest normal)
- Required field validation on ticket creation

---

## Class and Method Reference

### `BusNovaTech`
Application entry point. Manages initialization, login and main menu.

- `main(String[] args)` — initialization, login and main flow

---

### `ConfiguracionSistema`
System configuration management and persistence to `config.json`.

- `existeConfiguracion()` — checks if `config.json` exists
- `cargarConfiguracion()` — loads configuration from JSON
- `guardarConfiguracion(ConfiguracionSistema)` — saves to JSON
- `ejecutarConfiguracionInicial()` — requests and creates initial configuration
- `validarLogin(String, String)` — validates user credentials

---

### `GestionBuses`
Linked list administration for system buses.

- `GestionBuses(ConfiguracionSistema)` — builds bus list from configuration
- `mostrarBuses()` — displays bus list with status
- `obtenerBusDisponiblePorTipo(String)` — finds available bus of given type

---

### `ColaPrioridad`
Priority queue for tickets ordered by bus type (P:3, D:2, N:1).

- `encolar(NodoTiquete)` — inserts ticket at its priority position
- `desencolar()` — removes and returns the front ticket
- `crearTiquete()` — prompts user for data and generates ticket with auto ID
- `mostrarCola()` — displays all tickets in queue
- `verFrente()` — returns front ticket without removing it
- `exportarTiquetes()` — converts queue to array for serialization
- `importarTiquetes(NodoTiquete[])` — reconstructs queue from array

---

### `NodoTiquete`
Data structure representing a ticket.

**Attributes:** `nombre`, `id`, `edad`, `moneda`, `horaCompra`, `horaAbordaje`, `servicio`, `tipoBus`

---

### `PersistenciaCola`
Ticket serialization and main ticket menu management.

- `serializarCola(ColaPrioridad, String)` — saves queue to JSON
- `deserializarCola(String)` — loads queue from JSON
- `gestionarTiquetes(ColaPrioridad, ModuloAtencionTiquetes, AsignacionColas)` — ticket management menu
- `guardarColas(AsignacionColas)` — saves queue state to `colas.txt`
- `cargarColas(AsignacionColas, GestionBuses)` — loads queue state from `colas.txt`

---

### `ModuloAtencionTiquetes`
Full ticket attendance process management (module 1.2).

- `atenderDesdeMenu(ColaPrioridad, AsignacionColas)` — attends ticket from menu
- `mostrarHistorial()` — displays attendance history
- `procesarAtencionDesdeCola(ColaPrioridad, boolean, AsignacionColas)` — processes full attendance
- `calcularCobro(NodoTiquete)` — calculates amount by service type
- `confirmarPago(NodoTiquete, double)` — requests payment confirmation
- `registrarAtendido(NodoTiquete, Bus, double)` — records attendance in history

---

### `HistorialAtenciones`
Attended ticket history using a linked list.

- `agregarRegistro(RegistroAtencion)` — adds record and saves to JSON
- `mostrarHistorial()` — displays all records
- `cargarHistorial()` — loads from `atendidos.json`
- `guardarHistorial()` — saves to `atendidos.json`

---

### `RegistroAtencion`
Data structure for a completed boarding. Contains passenger, bus, terminal, time and amount.

---

### `AsignacionColas`
Ticket-to-bus assignment and queue state management (module 1.3).

- `asignarTiquete(String)` — assigns ticket to bus by type and rules
- `decrementarCola(String)` — decrements queue when ticket is attended
- `obtenerEstadoColas()` — returns current state of all queues
- `agregarBus(Bus, int)` — adds bus with initial count
- `actualizarCantidadBus(String, int)` — updates count for an existing bus

---

### `GestorIdPasajero`
Generates auto-incremental IDs using the maximum existing ID across `tiquetes.json` and `atendidos.json`.

- `obtenerSiguienteId()` — returns the next available ID
- `obtenerMaxIdDesdeTiquetes()` — maximum ID in `tiquetes.json`
- `obtenerMaxIdDesdeAtendidos()` — maximum ID in `atendidos.json`

---

### `GrafoRutas`
Weighted directed graph for routes between localities, implemented with arrays.

- `agregarVertice(Localidad)` — adds locality to the graph
- `agregarArista(Localidad, Localidad, int)` — adds directed weighted edge
- `imprimir()` — text representation of the full graph
- `rutaMasCorta(Localidad, Localidad)` — shortest path with Dijkstra
- `obtenerVertices()` — returns vertex array for serialization
- `cargarVertices(VerticeGrafo[])` — loads vertices from array

---

### `GestionGrafo`
Module 1.4 management: menu, persistence and graph queries.

- `gestionarGrafo()` — module main menu
- `guardarGrafo()` — saves to `grafo.json`
- `cargarGrafo()` — loads from `grafo.json`
- `agregarLocalidad()` — adds locality from menu
- `agregarRuta()` — adds route from menu
- `consultarRutaMasCorta()` — queries shortest path between two localities

---

### `ServicioBCCR`
Costa Rica Central Bank Web Service consumer.

- `obtenerIndicador(...)` — retrieves economic indicator from BCCR via HTTP POST
- `obtenerTipoCambioVenta()` — retrieves dollar selling exchange rate (indicator 318)

---

### `GestionTipoCambio`
User interface for exchange rate queries.

- `consultarTipoCambio()` — queries and displays exchange rate via JOptionPane
- `extraerTipoCambio(IndicadorEconomico)` — extracts value from XML response

---

## Development Notes

- Only standard Java libraries and Gson are used — no external frameworks
- All data structures (linked lists, queues, graphs) implemented from scratch
- Ticket attendance is manual via the "Board" option — not automatic on creation
- The graph exclusively uses arrays; no class from the Java Collections framework
- BCCR credentials must be configured before using module 1.5 in a real environment
