package com.documax.dot.domain.liquidacion;


import com.documax.dot.domain.equipo.Equipo;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Table(name="liquidaciones")
@Entity(name="Liquidacion")
@EqualsAndHashCode(of="id")
public class Liquidacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Equipo equipo;
    private long cargoBasico;
    private Date fechaInicio;
    private Date fechaFinal;
    private long diasTotales;
    private long contadorPagInicial;
    private long contadorPagFinal;
    private long totalPaginas;
    private long contadorScanInicial;
    private long contadorScanFinal;
    private long totalScan;
    private long pagAdicionales;
    private long scanAdicionales;
    private boolean pagoMinimo;
    private long costoPagAdicionales;
    private long costoScanAdicional;
    private long totalLiquidacion;
    private String nota1;
    private String nota2;

    public Liquidacion() {
    }

    public Liquidacion(Long id, Equipo equipo, long cargoBasico,Date fechaInicio, Date fechaFinal, long diasTotales,
                       long contadorPagInicial, long contadorPagFinal, long totalPaginas,
                       long contadorScanInicial, long contadorScanFinal, long totalScan,
                       long pagAdicionales, long scanAdicionales, boolean pagoMinimo, long costoPagAdicionales,
                       long costoScanAdicional, long totalLiquidacion, String nota1, String nota2) {
        this.id = id;
        this.equipo = equipo;
        this.cargoBasico=cargoBasico;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.diasTotales = diasTotales;
        this.contadorPagInicial = contadorPagInicial;
        this.contadorPagFinal = contadorPagFinal;
        this.totalPaginas = totalPaginas;
        this.contadorScanInicial = contadorScanInicial;
        this.contadorScanFinal = contadorScanFinal;
        this.totalScan = totalScan;
        this.pagAdicionales = pagAdicionales;
        this.scanAdicionales = scanAdicionales;
        this.pagoMinimo = pagoMinimo;
        this.costoPagAdicionales = costoPagAdicionales;
        this.costoScanAdicional = costoScanAdicional;
        this.totalLiquidacion = totalLiquidacion;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public long getCargoBasico() {
        return cargoBasico;
    }

    public void setCargoBasico(long cargoBasico) {
        this.cargoBasico = cargoBasico;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public long getDiasTotales() {
        return diasTotales;
    }

    public void setDiasTotales(long diasTotales) {
        this.diasTotales = diasTotales;
    }

    public long getContadorPagInicial() {
        return contadorPagInicial;
    }

    public void setContadorPagInicial(long contadorPagInicial) {
        this.contadorPagInicial = contadorPagInicial;
    }

    public long getContadorPagFinal() {
        return contadorPagFinal;
    }

    public void setContadorPagFinal(long contadorPagFinal) {
        this.contadorPagFinal = contadorPagFinal;
    }

    public long getContadorScanInicial() {
        return contadorScanInicial;
    }

    public void setContadorScanInicial(long contadorScanInicial) {
        this.contadorScanInicial = contadorScanInicial;
    }

    public long getContadorScanFinal() {
        return contadorScanFinal;
    }

    public void setContadorScanFinal(long contadorScanFinal) {
        this.contadorScanFinal = contadorScanFinal;
    }

    public long getPagAdicionales() {
        return pagAdicionales;
    }

    public void setPagAdicionales(long pagAdicionales) {
        this.pagAdicionales = pagAdicionales;
    }

    public long getScanAdicionales() {
        return scanAdicionales;
    }

    public void setScanAdicionales(long scanAdicionales) {
        this.scanAdicionales = scanAdicionales;
    }

    public boolean isPagoMinimo() {
        return pagoMinimo;
    }

    public void setPagoMinimo(boolean pagoMinimo) {
        this.pagoMinimo = pagoMinimo;
    }

    public long getCostoPagAdicionales() {
        return costoPagAdicionales;
    }

    public void setCostoPagAdicionales(long costoPagAdicionales) {
        this.costoPagAdicionales = costoPagAdicionales;
    }

    public long getCostoScanAdicional() {
        return costoScanAdicional;
    }

    public void setCostoScanAdicional(long costoScanAdicional) {
        this.costoScanAdicional = costoScanAdicional;
    }

    public long getTotalLiquidacion() {
        return totalLiquidacion;
    }

    public void setTotalLiquidacion(long totalLiquidacion) {
        this.totalLiquidacion = totalLiquidacion;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public long getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(long totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public long getTotalScan() {
        return totalScan;
    }

    public void setTotalScan(long totalScan) {
        this.totalScan = totalScan;
    }
}
