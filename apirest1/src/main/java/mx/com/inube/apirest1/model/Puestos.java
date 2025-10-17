package mx.com.inube.apirest1.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PUESTOS")
public class Puestos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PUESTO")
    private Integer idPuesto;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "puesto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Empleado> empleados;

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
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

