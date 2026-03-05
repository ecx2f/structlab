# BusNovaTech

University project — **Data Structures** course, Universidad Fidélitas, Costa Rica.

Bus terminal management system built with pure Java: all data structures (linked lists, priority queues, weighted directed graphs) implemented from scratch without the Java Collections framework.

> Spanish version: [README.es.md](./README.es.md)

---

## Tech stack

| | |
|---|---|
| Language | Java 24 |
| Build | Maven |
| JSON serialization | Gson 2.8.9 |
| UI | Swing (`JOptionPane`) |
| Data structures | Linked lists, priority queue, weighted directed graph |

---

## System flow

1. **Startup** — checks for `config.json`
2. **Initial setup** — if not found, prompts for terminal name, bus count and two user credentials
3. **Login** — authenticate with configured credentials
4. **Main menu** — access to all modules

---

## How to run

```bash
mvn compile exec:java
```

### First run

The system will ask for:
- Terminal name
- Number of buses (minimum 3 — 1 priority, 1 direct, the rest normal)
- User 1 and password
- User 2 and password

This is saved to `config.json` and will not be asked again.

### BCCR credentials (Module 1.5)

The exchange rate module requires credentials for the Costa Rica Central Bank Web Service.
Before running, edit `ServicioBCCR.java` and replace the placeholders:

```
BCCR_NOMBRE  →  name registered with BCCR
BCCR_EMAIL   →  email registered with BCCR
BCCR_TOKEN   →  BCCR access token
```

### Generated data files

| File | Contents |
|---|---|
| `config.json` | System configuration (terminal, buses, users) |
| `tiquetes.json` | Pending ticket queue |
| `atendidos.json` | Attended ticket history |
| `colas.txt` | People count per bus queue |
| `grafo.json` | Route graph between localities |

---

## Module status

| Module | Description | Status |
|---|---|---|
| 1.0 | Data structure configuration | ✅ |
| 1.1 | Ticket creation | ✅ |
| 1.2 | Ticket attendance | ✅ |
| 1.3 | Queue filling | ✅ |
| 1.4 | Complementary services (Graphs) | ✅ |
| 1.5 | Exchange rate query (BCCR) | ✅ |

---

## Documentation

- [Modules](./docs/en/modules.md) — goals, features and requirements per module
- [Architecture](./docs/en/architecture.md) — class overview, file tree and method reference
- [Changelog](./docs/en/changelog.md) — fixes and improvements per submission

> Spanish docs: [docs/es/](./docs/es/)

---

See [CONTRIBUTORS.md](./CONTRIBUTORS.md) for the development team.
