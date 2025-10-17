package mx.com.inube.apirest1.repository;

import mx.com.inube.apirest1.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository  extends JpaRepository<Empresa, Integer> {
}
