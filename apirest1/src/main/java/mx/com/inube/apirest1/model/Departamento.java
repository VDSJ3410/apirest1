package mx.com.inube.apirest1.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDEPARTAMENTO")
    private Integer idDepartamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDEMPRESA")
    private Empresa empresa;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Empleado> empleados;

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
