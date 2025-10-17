package mx.com.inube.apirest1.controller;

import mx.com.inube.apirest1.model.Empleado;
import mx.com.inube.apirest1.service.DepartamentoService;
import mx.com.inube.apirest1.service.EmpleadoService;
import mx.com.inube.apirest1.service.PuestosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empService;
    private final DepartamentoService deptoService;
    private final PuestosService puestosService;

    public EmpleadoController(EmpleadoService empService, DepartamentoService deptoService, PuestosService puestosService){
        this.empService = empService;
        this.deptoService = deptoService;
        this.puestosService = puestosService;
    }

    @GetMapping
    public List<Empleado> all(){
        return empService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> get(@PathVariable Integer id){
        return empService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empleado> create(@RequestBody Empleado empleado){
        if (empleado.getDepartamento() == null || empleado.getDepartamento().getIdDepartamento() == null){
            return ResponseEntity.badRequest().build();
        }

        return deptoService.findById(empleado.getDepartamento().getIdDepartamento())
                .map(depto -> {
                    empleado.setDepartamento(depto);

                    // Asignar el puesto si viene
                    if (empleado.getPuesto() != null && empleado.getPuesto().getIdPuesto() != null){
                        puestosService.findById(empleado.getPuesto().getIdPuesto())
                                .ifPresent(empleado::setPuesto);
                    }

                    Empleado saved = empService.save(empleado);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(saved.getIdEmpleado())
                            .toUri();
                    return ResponseEntity.created(location).body(saved);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable Integer id, @RequestBody Empleado empleado){
        return empService.findById(id)
                .map(existing -> {
                    existing.setNombre(empleado.getNombre());
                    existing.setEdad(empleado.getEdad());
                    existing.setFechaNacimiento(empleado.getFechaNacimiento());

                    if (empleado.getDepartamento() != null){
                        deptoService.findById(empleado.getDepartamento().getIdDepartamento())
                                .ifPresent(existing::setDepartamento);
                    }

                    if (empleado.getPuesto() != null && empleado.getPuesto().getIdPuesto() != null){
                        puestosService.findById(empleado.getPuesto().getIdPuesto())
                                .ifPresent(existing::setPuesto);
                    }

                    Empleado updated = empService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        return empService.findById(id)
                .map(existing -> {
                    empService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}



