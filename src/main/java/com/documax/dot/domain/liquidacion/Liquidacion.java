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

    @ManyToOne
    private Equipo equipo;
    private long idEventoAnt;
    private long idEventoAct;
    private long diasTotales;
    private long totalPaginas;
    private long totalScan;
    private long pagAdicionales;
    private long scanAdicionales;
    private long costoPagAdicionales;
    private long costoScanAdicional;
    private long totalLiquidacion;
    private String nota1;
    private String nota2;

    public Liquidacion() {
    }

    public Liquidacion(Long id, Equipo equipo, long idEventoAnt, long idEventoAct, long diasTotales,
                       long totalPaginas, long totalScan, long pagAdicionales, long scanAdicionales,
                       long costoPagAdicionales, long costoScanAdicional, long totalLiquidacion, String nota1, String nota2) {
        this.id = id;
        this.equipo = equipo;
        this.idEventoAnt = idEventoAnt;
        this.idEventoAct = idEventoAct;
        this.diasTotales = diasTotales;
        this.totalPaginas = totalPaginas;
        this.totalScan = totalScan;
        this.pagAdicionales = pagAdicionales;
        this.scanAdicionales = scanAdicionales;
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

    public long getIdEventoAnt() {
        return idEventoAnt;
    }

    public void setIdEventoAnt(long idEventoAnt) {
        this.idEventoAnt = idEventoAnt;
    }

    public long getIdEventoAct() {
        return idEventoAct;
    }

    public void setIdEventoAct(long idEventoAct) {
        this.idEventoAct = idEventoAct;
    }

    public long getDiasTotales() {
        return diasTotales;
    }

    public void setDiasTotales(long diasTotales) {
        this.diasTotales = diasTotales;
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
}