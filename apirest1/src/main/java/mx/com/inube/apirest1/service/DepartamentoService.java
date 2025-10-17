package mx.com.inube.apirest1.service;

import mx.com.inube.apirest1.model.Departamento;
import mx.com.inube.apirest1.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository repo){
        this.departamentoRepository = repo;
    }
    public List<Departamento> findAll(){
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> findById(Integer id){
        return departamentoRepository.findById(id);
    }

    public List<Departamento> findByEmpresa(Integer empresaId){
        return departamentoRepository.findByEmpresaIdEmpresa(empresaId);
    }

    public Departamento save(Departamento departamento){
        return departamentoRepository.save(departamento);
    }

    public void delete(Integer id){
        departamentoRepository.deleteById(id);
    }
}
