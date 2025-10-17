package mx.com.inube.apirest1.service;

import mx.com.inube.apirest1.model.Reporte;
import mx.com.inube.apirest1.repository.ReporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {
    private final ReporteRepository reporteRepository;

    public ReporteService (ReporteRepository repo ) {this.reporteRepository = repo;}

    public List<Reporte> findAll() {return reporteRepository.findAll();}
}
