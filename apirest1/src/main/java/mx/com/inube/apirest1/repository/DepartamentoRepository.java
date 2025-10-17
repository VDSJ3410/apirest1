package mx.com.inube.apirest1.repository;

import mx.com.inube.apirest1.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    List<Departamento> findByEmpresaIdEmpresa(Integer idEmpresa);

}
