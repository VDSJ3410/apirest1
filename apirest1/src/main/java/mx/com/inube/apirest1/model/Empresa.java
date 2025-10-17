package mx.com.inube.apirest1.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "EMPRESA")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEMPRESA")
    private Integer idEmpresa;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Departamento> departamentos;

    public Integer getIdEmpresa(){
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa){
        this.idEmpresa = idEmpresa;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public List<Departamento> getDepartamentos(){
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}
