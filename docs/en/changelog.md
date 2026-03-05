# Changelog

---

## Second Submission

### Module 1.2 — Fixes

- Fixed ticket persistence in `tiquetes.json`
- Correct billing calculation by service type
- Payment validation before marking ticket as attended
- Correct rejection handling (passenger removed from queue)
- Full integration with management menu
- Automatic persistence after each operation
- Improved queue and history display via JOptionPane

### Module 1.3 — Fixes

- Full integration with modules 1.1 and 1.2
- Fixed duplicate entries in `colas.txt` — now updates counts instead of appending new entries
- Automatic ticket-to-bus assignment on ticket creation
- Automatic queue update when attending tickets
- Correct persistence and loading from `colas.txt`
- P/D/N code compatibility with full type names in assignment logic

### Module 1.4 — New

- `GrafoRutas`: array-based weighted directed graph with Dijkstra's algorithm
- `GestionGrafo`: menu, persistence to `grafo.json` and integration in main menu
- Support classes: `Localidad`, `VerticeGrafo`, `AristaGrafo`

### Module 1.5 — New

- Integration with the Costa Rica Central Bank Web Service
- Online exchange rate query and automatic amount conversion to colones
- Integration in module 1.2 billing calculations
- Manual query menu option
- Error handling when service is unavailable

### General Improvements

- `GestorIdPasajero`: automatic ID generation based on the maximum existing ID across `tiquetes.json` and `atendidos.json`, preventing cross-session duplicates
- Module 1.3 integrated into the ticket management menu (automatic assignment + queue status view)
- Queue decrement when attending tickets, for both accepted and rejected payments
