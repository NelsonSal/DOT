package com.documax.dot.domain.contrato;

import com.documax.dot.domain.dto.DatosRegistroContrato;
import com.documax.dot.domain.equipo.Equipo;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Table(name="contratos")
@Entity(name="Contrato")
@EqualsAndHashCode(of="id")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refContrato;
    private Date fechaContrato;
    private Long cupoPaginas;
    private Long costoPagina;
    private Long costoPaginaAdicional;
    private Long cupoScan;
    private Long costoScanAdicional;
    private Long cargoBasico;
    private String detalles;
    private String departamento="";
    private String ipAddr="";
    @OneToOne
    private Equipo equipo;

    public Contrato() {
    }

    public Contrato(Long id, String refContrato, Date fechaContrato, Long cupoPaginas,
                    Long costoPagina, Long costoPaginaAdicional, Long cupoScan,
                    Long costoScanAdicional, Long cargoBasico, String detalles, Equipo equipo, String departamento, String ipAddr) {
        this.id = id;
        this.refContrato = refContrato;
        this.fechaContrato = fechaContrato;
        this.cupoPaginas = cupoPaginas;
        this.costoPagina = costoPagina;
        this.costoPaginaAdicional = costoPaginaAdicional;
        this.cupoScan = cupoScan;
        this.costoScanAdicional = costoScanAdicional;
        this.cargoBasico=cargoBasico;
        this.detalles = detalles;
        this.equipo = equipo;
        this.departamento=departamento;
        this.ipAddr=ipAddr;
    }

    public Contrato(DatosRegistroContrato datosRegistroContrato) {
        this.refContrato = datosRegistroContrato.refContrato();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            this.fechaContrato = (formatter.parse(datosRegistroContrato.fechaContrato()));
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.cupoPaginas = datosRegistroContrato.cupoPaginas();
        this.costoPagina = datosRegistroContrato.costoPagina();
        this.costoPaginaAdicional = datosRegistroContrato.costoPaginaAdicional();
        this.cupoScan = datosRegistroContrato.cupoScan();
        this.costoScanAdicional = datosRegistroContrato.costoScanAdicional();
        this.cargoBasico=datosRegistroContrato.costoPagina()*datosRegistroContrato.cupoPaginas();
        this.detalles = datosRegistroContrato.detalles();
        this.equipo = datosRegistroContrato.equipo();
        this.departamento=datosRegistroContrato.departamento();
        this.ipAddr=datosRegistroContrato.ipAddr();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefContrato() {
        return refContrato;
    }

    public void setRefContrato(String refContrato) {
        this.refContrato = refContrato;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Long getCupoPaginas() {
        return cupoPaginas;
    }

    public void setCupoPaginas(Long cupoPaginas) {
        this.cupoPaginas = cupoPaginas;
    }

    public Long getCostoPagina() {
        return costoPagina;
    }

    public void setCostoPagina(Long costoPagina) {
        this.costoPagina = costoPagina;
    }

    public Long getCostoPaginaAdicional() {
        return costoPaginaAdicional;
    }

    public void setCostoPaginaAdicional(Long costoPaginaAdicional) {
        this.costoPaginaAdicional = costoPaginaAdicional;
    }

    public Long getCupoScan() {
        return cupoScan;
    }

    public void setCupoScan(Long cupoScan) {
        this.cupoScan = cupoScan;
    }

    public Long getCostoScanAdicional() {
        return costoScanAdicional;
    }

    public void setCostoScanAdicional(Long costoScanAdicional) {
        this.costoScanAdicional = costoScanAdicional;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Long getCargoBasico() {
        return cargoBasico;
    }

    public void setCargoBasico(Long cargoBasico) {
        this.cargoBasico = cargoBasico;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}
