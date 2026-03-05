# System Modules

BusNovaTech is composed of 6 modules covering everything from initial setup to integration with external services.

---

## Module 1.0 — Data Structure Configuration

**Goal:** Set up the system's initial environment and persist configuration to `config.json`.

| Feature | Description |
|---|---|
| Config detection | Checks for `config.json` on startup |
| Initial setup | Registers terminal name, buses and users |
| Bus validation | 1 priority bus, 1 direct bus, rest normal |
| User management | Basic authentication system |
| JSON persistence | Save and load configuration |

**Data managed:**
- Terminal name
- Total number of buses (dynamic)
- Bus type distribution
- Usernames and passwords

**Requirements covered:**
- Config detection and loading from `config.json`
- Initial system setup (terminal, buses, users)
- Bus distribution validation (1 priority, 1 direct, rest normal)
- Authentication system with at least 2 users
- Configuration persistence in JSON
- Dynamic bus loading from configuration

---

## Module 1.1 — Ticket Creation

**Goal:** Manage ticket creation with priority queues based on service type and bus.

| Feature | Description |
|---|---|
| Ticket creation | Passenger registration interface |
| Priority queue | Ordered by bus type |
| Data validation | Required fields and format |
| JSON persistence | Saved to `tiquetes.json` |
| Ticket management | Menu to manage tickets |

**Ticket structure:**

| Field | Description |
|---|---|
| `nombre` | Passenger name |
| `id` | Auto-incremental, generated automatically |
| `edad` | Passenger age |
| `moneda` | Amount to pay (calculated at boarding) |
| `horaCompra` | Purchase timestamp (automatic) |
| `horaAbordaje` | Boarding timestamp (`-1` if pending) |
| `servicio` | VIP, Regular, Cargo or Executive |
| `tipoBus` | Classification: P, D or N |

IDs are generated automatically using the maximum existing ID across `tiquetes.json` and `atendidos.json`, preventing duplicates between sessions.

**Requirements covered:**
- Ticket creation with full passenger info
- Priority queue by bus type (P/D/N)
- Input data validation
- Automatic purchase timestamp assignment
- Auto-incremental ID based on existing maximum
- Persistence in `tiquetes.json`
- Load pending tickets on startup
- Ticket queue display

---

## Module 1.2 — Ticket Attendance

**Goal:** Manage inspector attendance, ensuring correct billing, timestamp updates and persistent records in `atendidos.json`.

| Feature | Description |
|---|---|
| "Board" action | Inspector manually attends the next passenger |
| Payment validation | Calculates amount and requests confirmation before boarding |
| Rejection handling | Passenger removed from queue if they don't pay |
| History record | Each boarding saved to `atendidos.json` |
| History display | View attendance history through interface |
| Billing calculation | VIP ($100), Regular ($20), Cargo ($20 + $10/lb), Executive ($1000) |
| Bus state management | Changes to "Attending" during process, returns to "Available" |

**Design note:** Only manual attendance via the "Board" menu option was implemented. This allows creating multiple tickets and deciding when to attend each one, making the system easier to demonstrate. Automatic attendance when a ticket is created in an empty queue can be implemented in future versions.

**Requirements covered:**
- "Board passenger" functionality from management menu
- Available bus assignment by ticket type
- Billing calculation by service type
- Payment confirmation dialog
- Rejection handling
- Boarding timestamp update at time of attendance
- Bus state change during process
- Full record in `atendidos.json` (passenger, bus, terminal, time, amount)
- Attendance history display
- Automatic persistence after each attendance

---

## Module 1.3 — Queue Filling

**Goal:** Implement automatic ticket-to-bus assignment based on specific rules, considering the current size of each bus's queue.

| Feature | Description |
|---|---|
| Automatic assignment | Ticket assigned to a bus upon creation |
| Assignment rules | Priority/Direct to their dedicated bus, Normal to shortest queue |
| Queue management | Tracks number of people per bus |
| Persistence | Saved to `colas.txt` (plain text) |
| Queue loading | Restores state on system startup |
| Queue update | Decrements queue when ticket is attended |
| Status display | Menu option to view all queue states |

**Assignment rules:**

| Ticket type | Rule |
|---|---|
| Priority (P) | Always assigned to the single P1 bus |
| Direct (D) | Always assigned to the single D1 bus |
| Normal (N) | Assigned to the normal bus with the shortest queue; ties go to the first found |

**Integration with other modules:**
- **1.1:** When creating a ticket, a bus is assigned and its queue incremented
- **1.2:** When a ticket is attended (payment accepted or rejected), the bus queue is decremented
- Queues are saved to `colas.txt` on exit and loaded on startup

**Requirements covered:**
- Automatic ticket-to-bus assignment on ticket creation
- Assignment rules correctly implemented
- Queue count management per bus
- Persistence in `colas.txt`
- Queue loading on startup
- Automatic update when attending tickets
- Queue status display
- Full integration with modules 1.1 and 1.2

---

## Module 1.4 — Complementary Services (Graphs)

**Goal:** Implement a weighted directed graph to define routes between localities and calculate the shortest path.

| Feature | Description |
|---|---|
| Weighted directed graph | Array-based implementation with edge weights |
| Locality definition | Manage stops from the runtime menu |
| Route definition | Directed weighted connections between localities |
| Graph display | Full visualization with localities and weights |
| Shortest path | Dijkstra's algorithm between two localities |
| JSON persistence | Save and load from `grafo.json` |

**Dijkstra's algorithm:**
- Implemented using only arrays (no `HashMap`, `PriorityQueue` or `ArrayList`)
- Calculates minimum weight and reconstructs the full path
- Displays route step by step with total weight

**Integration with the system:**
- Accessible from the main menu as "Complementary services (Graphs)"
- Graph is automatically saved to `grafo.json` on modification
- Loaded on system startup
- This service is not billed to the client

**Requirements covered:**
- Weighted directed graph using arrays
- Locality and route definition at runtime
- Graph display
- Shortest path with Dijkstra
- Persistence in `grafo.json`
- Automatic loading on startup
- Management menu integrated into the system

---

## Module 1.5 — BCCR Exchange Rate Query

**Goal:** Integrate an online exchange rate query from the Costa Rica Central Bank (BCCR) Web Service and use it in billing calculations.

| Feature | Description |
|---|---|
| Web Service integration | BCCR service consumption for exchange rate |
| Real-time query | Dollar selling rate (indicator 318) |
| Amount conversion | Automatic billing in colones (CRC) |
| Billing integration | Used when attending tickets in module 1.2 |
| Manual query | Menu option to check current exchange rate |
| Error handling | If service unavailable, attendance is cancelled |

**Technical details:**
- **Endpoint:** `https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicos`
- **Indicator:** 318 (dollar selling exchange rate)
- **Response format:** XML deserialized with JAXB
- **Communication:** HTTP POST with Java's `HttpClient`

**Integration with the system:**
- When attending a ticket, the exchange rate is queried and the amount in colones is calculated
- The system displays the amount in dollars and its equivalent in colones when confirming payment
- Available manually from the main menu

**Requirements covered:**
- Integration with the Costa Rica Central Bank Web Service
- Online exchange rate query (indicator 318)
- Exchange rate integration in billing calculations
- Automatic amount conversion to colones
- Menu option for manual query
- Error handling when service is unavailable
- XML deserialization using JAXB

---

## Priority System

The priority queue organizes tickets with the following criteria:

| Priority | Bus Type | Code | Value |
|---|---|---|---|
| High | Priority | P | 3 |
| Medium | Direct | D | 2 |
| Low | Normal | N | 1 |

Higher priority tickets are attended first regardless of arrival order.
