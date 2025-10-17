package mx.com.inube.apirest1.service;

import mx.com.inube.apirest1.model.Sucursales;
import mx.com.inube.apirest1.repository.SucursalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalesService {

    private final SucursalesRepository sucursalesRepository;

    public SucursalesService(SucursalesRepository repo){
        this.sucursalesRepository = repo;
    }

    public List<Sucursales> findAll(){
        return sucursalesRepository.findAll();
    }

    public Optional<Sucursales> findById(Integer id){
        return sucursalesRepository.findById(id);
    }

    public Sucursales save(Sucursales sucursal){
        return sucursalesRepository.save(sucursal);
    }

    public void delete(Integer id){
        sucursalesRepository.deleteById(id);
    }
}

