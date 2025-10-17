package mx.com.inube.apirest1.controller;

import mx.com.inube.apirest1.model.Sucursales;
import mx.com.inube.apirest1.service.SucursalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalesController {

    private final SucursalesService sucursalesService;

    public SucursalesController(SucursalesService service) {
        this.sucursalesService = service;
    }

    @GetMapping
    public List<Sucursales> findAll() {
        return sucursalesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursales> findById(@PathVariable Integer id) {
        Optional<Sucursales> sucursal = sucursalesService.findById(id);
        return sucursal.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sucursales create(@RequestBody Sucursales sucursal) {
        return sucursalesService.save(sucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursales> update(@PathVariable Integer id, @RequestBody Sucursales sucursalDetails) {
        Optional<Sucursales> existing = sucursalesService.findById(id);
        if (existing.isPresent()) {
            Sucursales s = existing.get();
            s.setNombre(sucursalDetails.getNombre());
            s.setDireccion(sucursalDetails.getDireccion());
            s.setCiudad(sucursalDetails.getCiudad());
            s.setFechaApertura(sucursalDetails.getFechaApertura());
            s.setEmpresa(sucursalDetails.getEmpresa());
            return ResponseEntity.ok(sucursalesService.save(s));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        sucursalesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}




