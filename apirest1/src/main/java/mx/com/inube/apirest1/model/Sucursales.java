package mx.com.inube.apirest1.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SUCURSALES")
public class Sucursales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUCURSAL")
    private Integer idSucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDEMPRESA", referencedColumnName = "IDEMPRESA")
    private Empresa empresa;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DIRECCION", nullable = false, length = 50)
    private String direccion;

    @Column(name = "CIUDAD", nullable = false, length = 50)
    private String ciudad;

    @Column(name = "FECHA_APERTURA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;

    public Integer getIdSucursal(){
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Empresa getEmpresa(){
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}
