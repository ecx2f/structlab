# Módulos del Sistema

BusNovaTech está compuesto por 6 módulos que cubren desde la configuración inicial hasta la integración con servicios externos.

---

## Módulo 1.0 — Configuración de Estructuras de Datos

**Objetivo:** Configurar el entorno inicial del sistema y persistir la información en `config.json`.

| Funcionalidad | Descripción |
|---|---|
| Detección de configuración | Verifica existencia de `config.json` al iniciar |
| Configuración inicial | Registro de terminal, buses y usuarios |
| Validación de buses | 1 preferencial, 1 directo, resto normales |
| Gestión de usuarios | Sistema de autenticación básico |
| Persistencia JSON | Guardado y carga de configuración |

**Datos manejados:**
- Nombre de la terminal
- Cantidad total de buses (dinámico)
- Distribución de tipos de buses
- Usuarios y contraseñas del sistema

**Requerimientos cubiertos:**
- Detección y carga de configuración desde `config.json`
- Configuración inicial del sistema (terminal, buses, usuarios)
- Validación de distribución de buses (1 preferencial, 1 directo, resto normales)
- Sistema de autenticación con al menos 2 usuarios
- Persistencia de configuración en formato JSON
- Carga dinámica de buses según configuración

---

## Módulo 1.1 — Creación de Tiquetes

**Objetivo:** Gestionar la creación de tiquetes con colas de prioridad según el tipo de servicio y bus.

| Funcionalidad | Descripción |
|---|---|
| Creación de tiquetes | Interfaz para registro de pasajeros |
| Cola de prioridad | Organización por tipo de bus |
| Validación de datos | Campos obligatorios y formato |
| Persistencia JSON | Guardado en `tiquetes.json` |
| Gestión de tiquetes | Menú para administrar tiquetes |

**Estructura del tiquete:**

| Campo | Descripción |
|---|---|
| `nombre` | Identificación del pasajero |
| `id` | Autoincremental, generado automáticamente |
| `edad` | Edad del pasajero |
| `moneda` | Monto a pagar (calculado al abordar) |
| `horaCompra` | Timestamp de compra (automático) |
| `horaAbordaje` | Timestamp de abordaje (`-1` si pendiente) |
| `servicio` | VIP, Regular, Carga o Ejecutivo |
| `tipoBus` | Clasificación: P, D o N |

Los IDs se generan automáticamente tomando el máximo existente en `tiquetes.json` y `atendidos.json`, evitando duplicados entre sesiones.

**Requerimientos cubiertos:**
- Creación de tiquetes con información completa del pasajero
- Cola de prioridad según tipo de bus (P/D/N)
- Validación de datos de entrada
- Asignación automática de hora de compra
- ID autoincremental basado en máximo existente
- Persistencia en `tiquetes.json`
- Carga de tiquetes pendientes al iniciar
- Visualización de cola de tiquetes

---

## Módulo 1.2 — Atención de Tiquetes

**Objetivo:** Administrar la atención por parte de los inspectores, garantizando cobro correcto, actualización de horarios y registro persistente en `atendidos.json`.

| Funcionalidad | Descripción |
|---|---|
| Acción "Abordar" | El inspector atiende manualmente al siguiente pasajero |
| Validación de pago | Calcula monto y solicita confirmación antes de abordar |
| Manejo de rechazos | Pasajero expulsado de la cola si no paga |
| Registro histórico | Cada abordaje se guarda en `atendidos.json` |
| Visualización de atendidos | Consulta del historial mediante interfaz |
| Cálculo de cobros | VIP ($100), Regular ($20), Carga ($20 + $10/lb), Ejecutivo ($1000) |
| Gestión de estado del bus | Cambia a "Atendiendo" durante el proceso y vuelve a "Disponible" |

**Nota de diseño:** Se implementó únicamente la atención manual mediante la opción "Abordar" del menú. Esto permite crear múltiples tiquetes y decidir cuándo atenderlos, facilitando la demostración del sistema. La atención automática al crear un tiquete en cola vacía puede implementarse en versiones futuras.

**Requerimientos cubiertos:**
- Funcionalidad "Abordar pasajero" desde menú de gestión
- Asignación de bus disponible según tipo de tiquete
- Cálculo de cobro por tipo de servicio
- Confirmación de pago mediante diálogo
- Manejo de rechazo de pago
- Actualización de hora de abordaje al momento de atención
- Cambio de estado del bus durante el proceso
- Registro completo en `atendidos.json` (pasajero, bus, terminal, hora, monto)
- Visualización de historial de atendidos
- Persistencia automática después de cada atención

---

## Módulo 1.3 — Llenado de las Colas

**Objetivo:** Implementar la asignación automática de tiquetes a buses según reglas específicas, considerando el tamaño actual de cada cola.

| Funcionalidad | Descripción |
|---|---|
| Asignación automática | Al crear tiquete, se asigna automáticamente a un bus |
| Reglas de asignación | Preferencial/Directo al único bus, Normal al de menor cola |
| Gestión de colas | Mantiene cantidad de personas por bus |
| Persistencia | Guardado en `colas.txt` (texto simple) |
| Carga de colas | Restaura estado al iniciar el sistema |
| Actualización | Decrementa cola al atender tiquetes |
| Visualización | Opción de menú para ver estado de todas las colas |

**Reglas de asignación:**

| Tipo de tiquete | Regla |
|---|---|
| Preferencial (P) | Siempre al único bus P1 |
| Directo (D) | Siempre al único bus D1 |
| Normal (N) | Al bus normal con menor cola; en empate, al primero encontrado |

**Integración con otros módulos:**
- **1.1:** Al crear tiquete se asigna bus y se incrementa su cola
- **1.2:** Al atender (pago aceptado o rechazado) se decrementa la cola del bus
- Las colas se guardan en `colas.txt` al cerrar y se cargan al iniciar

**Requerimientos cubiertos:**
- Asignación automática de tiquetes a buses al crear tiquete
- Reglas de asignación implementadas correctamente
- Gestión de cantidades en cola por bus
- Persistencia en `colas.txt`
- Carga de colas al iniciar
- Actualización automática al atender tiquetes
- Visualización del estado de colas
- Integración completa con módulos 1.1 y 1.2

---

## Módulo 1.4 — Servicios Complementarios (Grafos)

**Objetivo:** Implementar un grafo ponderado dirigido para definir rutas entre localidades y calcular la ruta más corta.

| Funcionalidad | Descripción |
|---|---|
| Grafo ponderado dirigido | Implementación con pesos en aristas usando arrays |
| Definición de localidades | Gestión de paradas desde el menú en ejecución |
| Definición de rutas | Conexiones dirigidas con peso entre localidades |
| Impresión del grafo | Visualización completa con localidades y pesos |
| Ruta más corta | Algoritmo de Dijkstra entre dos localidades |
| Persistencia JSON | Guardado y carga en `grafo.json` |

**Algoritmo de Dijkstra:**
- Implementado usando solo arrays (sin `HashMap`, `PriorityQueue` ni `ArrayList`)
- Calcula el peso mínimo y reconstruye el camino completo
- Muestra la ruta paso a paso con el peso total

**Integración con el sistema:**
- Accesible desde el menú principal como "Servicios complementarios (Grafos)"
- El grafo se guarda automáticamente en `grafo.json` al modificar
- Se carga al iniciar el sistema
- Este servicio no se cobra al cliente

**Requerimientos cubiertos:**
- Grafo ponderado dirigido usando arrays
- Definición de localidades y rutas desde ejecución
- Impresión del grafo construido
- Ruta más corta con Dijkstra
- Persistencia en `grafo.json`
- Carga automática al iniciar
- Menú de gestión integrado al sistema

---

## Módulo 1.5 — Consulta de Tipo de Cambio BCCR

**Objetivo:** Integrar la consulta en línea del tipo de cambio del dólar desde el Web Service del Banco Central de Costa Rica y utilizarlo en el cálculo de cobros.

| Funcionalidad | Descripción |
|---|---|
| Integración Web Service | Consumo del servicio BCCR para tipo de cambio |
| Consulta en tiempo real | Tipo de cambio de venta del dólar (indicador 318) |
| Conversión de montos | Cálculo automático de cobros en colones (CRC) |
| Integración en cobros | Se usa al atender tiquetes en módulo 1.2 |
| Consulta manual | Opción de menú para consultar el tipo de cambio |
| Manejo de errores | Si el servicio no está disponible, se cancela la atención |

**Detalles técnicos:**
- **Endpoint:** `https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicos`
- **Indicador:** 318 (tipo de cambio de venta del dólar)
- **Formato de respuesta:** XML deserializado con JAXB
- **Comunicación:** HTTP POST con `HttpClient` de Java

**Integración con el sistema:**
- Al atender un tiquete, se consulta el tipo de cambio y se calcula el monto en colones
- El sistema muestra monto en dólares y su equivalente en colones al confirmar el pago
- Accesible manualmente desde el menú principal

**Requerimientos cubiertos:**
- Integración con Web Service del Banco Central de Costa Rica
- Consulta de tipo de cambio en línea (indicador 318)
- Integración del tipo de cambio en el cálculo de cobros
- Conversión automática de montos a colones
- Opción de menú para consulta manual
- Manejo de errores cuando el servicio no está disponible
- Deserialización XML usando JAXB

---

## Sistema de Prioridades

La cola de prioridad organiza los tiquetes con el siguiente criterio:

| Prioridad | Tipo de Bus | Código | Valor |
|---|---|---|---|
| Alta | Preferencial | P | 3 |
| Media | Directo | D | 2 |
| Baja | Normal | N | 1 |

Los tiquetes de mayor prioridad se atienden primero independientemente del orden de llegada.
