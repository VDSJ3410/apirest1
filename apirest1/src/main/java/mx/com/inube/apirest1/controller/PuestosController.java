package mx.com.inube.apirest1.controller;

import mx.com.inube.apirest1.model.Puestos;
import mx.com.inube.apirest1.service.PuestosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/puestos")
public class PuestosController {

    private final PuestosService puestosService;

    public PuestosController(PuestosService service) {
        this.puestosService = service;
    }

    @GetMapping
    public List<Puestos> findAll() {
        return puestosService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Puestos> findById(@PathVariable Integer id) {
        Optional<Puestos> puesto = puestosService.findById(id);
        return puesto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Puestos create(@RequestBody Puestos puesto) {
        return puestosService.save(puesto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Puestos> update(@PathVariable Integer id, @RequestBody Puestos puestoDetails) {
        Optional<Puestos> existing = puestosService.findById(id);
        if (existing.isPresent()) {
            Puestos p = existing.get();
            p.setNombre(puestoDetails.getNombre());
            return ResponseEntity.ok(puestosService.save(p));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        puestosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


