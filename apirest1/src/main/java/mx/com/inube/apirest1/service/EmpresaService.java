package mx.com.inube.apirest1.service;

import mx.com.inube.apirest1.model.Empresa;
import mx.com.inube.apirest1.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository repo){
        this.empresaRepository = repo;
    }

    public List<Empresa> findAll(){
        return empresaRepository.findAll();
    }

    public Optional<Empresa> findById(Integer id){
        return empresaRepository.findById(id);
    }

    public Empresa save(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public void delete(Integer id){
        empresaRepository.deleteById(id);
    }
}
