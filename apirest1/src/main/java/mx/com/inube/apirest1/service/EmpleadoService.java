package mx.com.inube.apirest1.service;

import mx.com.inube.apirest1.model.Departamento;
import mx.com.inube.apirest1.model.Empleado;
import mx.com.inube.apirest1.model.Puestos;
import mx.com.inube.apirest1.repository.DepartamentoRepository;
import mx.com.inube.apirest1.repository.EmpleadoRepository;
import mx.com.inube.apirest1.repository.PuestosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final PuestosRepository puestosRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository,
                           DepartamentoRepository departamentoRepository,
                           PuestosRepository puestosRepository) {
        this.empleadoRepository = empleadoRepository;
        this.departamentoRepository = departamentoRepository;
        this.puestosRepository = puestosRepository;
    }

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> findById(Integer id) {
        return empleadoRepository.findById(id);
    }

    public List<Empleado> findByDepartamento(Integer deptoId) {
        return empleadoRepository.findByDepartamento_IdDepartamento(deptoId);
    }

    public List<Empleado> findByPuestos(Integer ptId) {
        return empleadoRepository.findByPuesto_IdPuesto(ptId);
    }

    public Empleado save(Empleado empleado) {
        Departamento dep = departamentoRepository.findById(
                empleado.getDepartamento().getIdDepartamento()
        ).orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        Puestos pue = puestosRepository.findById(
                empleado.getPuesto().getIdPuesto()
        ).orElseThrow(() -> new RuntimeException("Puesto no encontrado"));

        empleado.setDepartamento(dep);
        empleado.setPuesto(pue);

        return empleadoRepository.save(empleado);
    }

    public void delete(Integer id) {
        empleadoRepository.deleteById(id);
    }
}

