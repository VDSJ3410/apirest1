package mx.com.inube.apirest1.controller;

import jakarta.servlet.Servlet;
import mx.com.inube.apirest1.model.Empresa;
import mx.com.inube.apirest1.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<Empresa> all(){
        return empresaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> get(@PathVariable Integer id){
        return empresaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> create(@RequestBody Empresa empresa){
        Empresa saved = empresaService.save(empresa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getIdEmpresa())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(@PathVariable Integer id, @RequestBody Empresa empresa){
        return empresaService.findById(id)
                .map(existing -> {
            existing.setNombre(empresa.getNombre());
            Empresa updated = empresaService.save(existing);
            return ResponseEntity.ok(updated);
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        return empresaService.findById(id)
                .map(existing -> {
                    empresaService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
