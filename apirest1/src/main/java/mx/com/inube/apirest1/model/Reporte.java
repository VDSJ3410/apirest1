package mx.com.inube.apirest1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table (name = "VIW_REPORTE_EMPLEADOS")
public class Reporte {

    @Id
    @Column(name = "INTEMPLEADO", nullable = false)
    private  Integer intEmpleado;

    @Column (name = "STRNOMEMPRESA", nullable = false, length = 50)
    private String strNomEmpresa;

    @Column(name = "STRSURNOM", nullable = false, length = 100)
    private String strSurNom;

    @Column (name = "DATFECHAPERTURA", nullable = false)
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    private LocalDate datFechApertura;

    @Column (name = "STRDEPNOM", nullable = false, length = 50)
    private String strDepNom;

    @Column (name = "STRNOMBREEMP", nullable = false, length = 50)
    private String strNomEmp;

    @Column(name = "INTEDADEMP", nullable = false)
    private Integer intEdadEmp;

    public Integer getIntEmpleado(){
        return intEmpleado;
    }

    public Integer getIntEdadEmp() {
        return intEdadEmp;
    }

    public String getStrSurNom() {
        return strSurNom;
    }

    public String getStrNomEmp() {
        return strNomEmp;
    }

    public String getStrDepNom() {
        return strDepNom;
    }

    public LocalDate getDatFechApertura() {
        return datFechApertura;
    }

    public String getStrNomEmpresa() {
        return strNomEmpresa;
    }

    public void setIntEmpleado(Integer intEmpleado) {
        this.intEmpleado = intEmpleado;
    }

    public void setStrNomEmpresa(String strNomEmpresa) {
        this.strNomEmpresa = strNomEmpresa;
    }

    public void setIntEdadEmp(Integer intEdadEmp) {
        this.intEdadEmp = intEdadEmp;
    }

    public void setStrSurNom(String strSurNom) {
        this.strSurNom = strSurNom;
    }

    public void setStrNomEmp(String strNombreEmp) {
        this.strNomEmp = strNombreEmp;
    }

    public void setStrDepNom(String strDepNom) {
        this.strDepNom = strDepNom;
    }

    public void setDatFechApertura(LocalDate datFechApertura) {
        this.datFechApertura = datFechApertura;
    }
}
