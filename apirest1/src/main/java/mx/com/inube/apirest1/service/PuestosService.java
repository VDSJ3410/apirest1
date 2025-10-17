package mx.com.inube.apirest1.service;

import mx.com.inube.apirest1.model.Puestos;
import mx.com.inube.apirest1.repository.PuestosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuestosService {

    private final PuestosRepository puestosRepository;

    public PuestosService(PuestosRepository repo) {
        this.puestosRepository = repo;
    }

    public List<Puestos> findAll() {
        return puestosRepository.findAll();
    }

    public Optional<Puestos> findById(Integer id) {
        return puestosRepository.findById(id);
    }

    public Puestos save(Puestos puesto) {
        return puestosRepository.save(puesto);
    }

    public void delete(Integer id) {
        puestosRepository.deleteById(id);
    }
}


