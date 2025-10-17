package mx.com.inube.apirest1.repository;

import mx.com.inube.apirest1.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    List<Empleado> findByDepartamento_IdDepartamento(Integer idDepartamento);
    List<Empleado> findByPuesto_IdPuesto(Integer idPuestos);

}

