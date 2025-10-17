package mx.com.inube.apirest1.controller;

import mx.com.inube.apirest1.model.Departamento;
import mx.com.inube.apirest1.service.DepartamentoService;
import mx.com.inube.apirest1.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService deptoService;
    private final EmpresaService empresaService;

    public DepartamentoController(DepartamentoService deptoService, EmpresaService empresaService){
        this.deptoService = deptoService;
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<Departamento> all(){
        return deptoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> get(@PathVariable Integer id){
        return deptoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empresa/{empresaId}")
    public List<Departamento> byEmpresa(@PathVariable Integer empresaId){
        return deptoService.findByEmpresa(empresaId);
    }

    @PostMapping
    public ResponseEntity<Departamento> create(@RequestBody Departamento depto){
        if (depto.getEmpresa() == null || depto.getEmpresa().getIdEmpresa() == null){
            return ResponseEntity.badRequest().build();
        }

        return empresaService.findById(depto.getEmpresa().getIdEmpresa())
                .map(emp ->{
                    depto.setEmpresa(emp);
                    Departamento saved = deptoService.save(depto);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(saved.getIdDepartamento())
                            .toUri();
                    return ResponseEntity.created(location).body(saved);
                })
                .orElse(ResponseEntity.badRequest().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> update(@PathVariable Integer id, @RequestBody Departamento depto){
        return deptoService.findById(id)
                .map(existing ->{
                    existing.setNombre(depto.getNombre());
                    if (depto.getEmpresa() != null){
                        empresaService.findById(depto.getEmpresa().getIdEmpresa())
                                .ifPresent(existing::setEmpresa);
                    }
                    Departamento updated = deptoService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        return deptoService.findById(id)
                .map(existing ->{
                    deptoService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
