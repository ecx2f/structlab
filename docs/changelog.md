# Changelog

---

## Segunda Entrega

### Módulo 1.2 — Correcciones

- Corrección de persistencia de tiquetes en `tiquetes.json`
- Implementación correcta del cálculo de cobros por tipo de servicio
- Validación de pago antes de marcar tiquete como atendido
- Manejo correcto de rechazo de pago (pasajero retirado de la cola)
- Integración completa con el menú de gestión
- Persistencia automática después de cada operación
- Visualización mejorada de cola e historial mediante JOptionPane

### Módulo 1.3 — Correcciones

- Integración completa con módulos 1.1 y 1.2
- Corrección de duplicados en `colas.txt` — ahora actualiza cantidades en lugar de agregar entradas nuevas
- Asignación automática de tiquetes a buses al crear tiquete
- Actualización automática de colas al atender tiquetes
- Persistencia y carga correcta desde `colas.txt`
- Compatibilidad de códigos P/D/N con nombres completos en la lógica de asignación

### Módulo 1.4 — Nuevo

- Implementación de `GrafoRutas`: grafo ponderado dirigido con arrays y algoritmo de Dijkstra
- Implementación de `GestionGrafo`: menú, persistencia en `grafo.json` e integración en menú principal
- Clases de soporte: `Localidad`, `VerticeGrafo`, `AristaGrafo`

### Módulo 1.5 — Nuevo

- Integración con Web Service del Banco Central de Costa Rica
- Consulta de tipo de cambio en línea y conversión automática de montos a colones
- Integración en el cálculo de cobros del módulo 1.2
- Opción de menú para consulta manual
- Manejo de errores cuando el servicio no está disponible

### Mejoras Generales

- `GestorIdPasajero`: generación automática de IDs basada en el máximo existente en `tiquetes.json` y `atendidos.json`, evitando duplicados entre sesiones
- Integración del módulo 1.3 en el menú de gestión de tiquetes (asignación automática + vista de estado de colas)
- Decremento de colas al atender tiquetes, tanto en pago aceptado como rechazado
