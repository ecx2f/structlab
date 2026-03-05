# BusNovaTech

Sistema de gestión de terminales y servicios de buses, desarrollado en Java con estructuras de datos implementadas desde cero (sin colecciones de Java).

---

## Tecnologías

| | |
|---|---|
| Lenguaje | Java 24 |
| Build | Maven |
| Serialización JSON | Gson 2.8.9 |
| Interfaz de usuario | Swing (`JOptionPane`) |
| Estructuras de datos | Listas enlazadas, colas de prioridad, grafos ponderados dirigidos |

---

## Flujo del sistema

1. **Inicialización** — Verifica si existe `config.json`
2. **Configuración inicial** — Si no existe, solicita nombre de terminal, cantidad de buses y credenciales de dos usuarios
3. **Autenticación** — Login con credenciales configuradas
4. **Menú principal** — Acceso a todos los módulos

---

## Cómo ejecutar

```bash
mvn compile exec:java
```

### Primera ejecución

El sistema solicita:
- Nombre de la terminal
- Cantidad de buses (mínimo 3 — se asigna 1 preferencial, 1 directo, el resto normales)
- Usuario 1 y contraseña
- Usuario 2 y contraseña

Esta información se guarda en `config.json` y no se vuelve a solicitar.

### Credenciales BCCR (Módulo 1.5)

El módulo de tipo de cambio requiere credenciales del Web Service del Banco Central de Costa Rica.
Antes de ejecutar, editar `ServicioBCCR.java` y reemplazar los placeholders:

```
BCCR_NOMBRE  →  nombre registrado en el BCCR
BCCR_EMAIL   →  email registrado en el BCCR
BCCR_TOKEN   →  token de acceso del BCCR
```

### Archivos de datos generados

| Archivo | Contenido |
|---|---|
| `config.json` | Configuración del sistema (terminal, buses, usuarios) |
| `tiquetes.json` | Cola de tiquetes pendientes |
| `atendidos.json` | Historial de tiquetes atendidos |
| `colas.txt` | Cantidad de personas en cola por bus |
| `grafo.json` | Grafo de rutas entre localidades |

---

## Estado del proyecto

| Módulo | Descripción | Estado |
|---|---|---|
| 1.0 | Configuración de estructuras de datos | ✅ |
| 1.1 | Creación de tiquetes | ✅ |
| 1.2 | Atención de tiquetes | ✅ |
| 1.3 | Llenado de las colas | ✅ |
| 1.4 | Servicios complementarios (Grafos) | ✅ |
| 1.5 | Consulta de tipo de cambio (BCCR) | ✅ |

---

## Documentación

- [Módulos](./docs/modules.md) — Objetivos, funcionalidades y requerimientos por módulo
- [Arquitectura](./docs/architecture.md) — Estructura de clases, árbol de archivos y referencia de métodos
- [Changelog](./docs/changelog.md) — Correcciones y mejoras por entrega

---

Ver [CONTRIBUTORS.md](./CONTRIBUTORS.md) para información del equipo de desarrollo.
