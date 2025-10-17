package mx.com.inube.apirest1.controller;

import mx.com.inube.apirest1.model.Reporte;
import mx.com.inube.apirest1.service.ReporteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController (ReporteService reporteService){this.reporteService = reporteService;}

    @GetMapping
    public List<Reporte> all(){return reporteService.findAll();}
}
