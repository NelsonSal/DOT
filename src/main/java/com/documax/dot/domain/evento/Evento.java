package com.documax.dot.domain.evento;

import com.documax.dot.domain.dto.DatosRegistroEvento;
import com.documax.dot.domain.equipo.Equipo;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Table(name="eventos")
@Entity(name="Evento")
@EqualsAndHashCode(of="id")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Equipo equipo;
    //@DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date fechaEvento;
    private Long contadorTotal;
    private Long contadorScan;

    private boolean tomaContador;

    private String detalle;
    private boolean chargeScan;

    public Evento() {
    }

    public Evento(Long id, Equipo equipo, Date fechaEvento, Long contadorTotal, Long contadorScan, boolean tomaContador, String detalle, boolean chargeScan) {
        this.id = id;
        this.equipo = equipo;
        this.fechaEvento = fechaEvento;
        this.contadorTotal = contadorTotal;
        this.contadorScan=contadorScan;
        this.tomaContador = tomaContador;
        this.detalle = detalle;
        this.chargeScan=chargeScan;
    }
    public Evento( Date fechaEvento, Long contadorTotal, Long contadorScan, boolean tomaContador, String detalle,boolean chargeScan) {
        this.fechaEvento = fechaEvento;
        this.contadorTotal = contadorTotal;
        this.contadorScan=contadorScan;
        this.tomaContador = tomaContador;
        this.detalle = detalle;
        this.chargeScan=chargeScan;
    }

    public Evento(DatosRegistroEvento datosRegistroEvento) {
        this.equipo = datosRegistroEvento.equipo();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fechaEvento = (formatter.parse(datosRegistroEvento.fechaEvento()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.contadorTotal = datosRegistroEvento.contadorTotal();
        this.contadorScan=datosRegistroEvento.contadorScan();
        //this.tomaContador = datosRegistroEvento.tomaContador(); Lo elimino mientras pruebo dejar fijo true para toma contador
        this.detalle = datosRegistroEvento.detalle();
        this.chargeScan=datosRegistroEvento.chargeScan();
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

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Long getContadorTotal() {
        return contadorTotal;
    }

    public void setContadorTotal(Long contadorTotal) {
        this.contadorTotal = contadorTotal;
    }

    public Long getContadorScan() {
        return contadorScan;
    }

    public void setContadorScan(Long contadorScan) {
        this.contadorScan = contadorScan;
    }



    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isChargeScan() {
        return chargeScan;
    }

    public void setChargeScan(boolean chargeScan) {
        this.chargeScan = chargeScan;
    }

    public boolean isTomaContador() {
        return tomaContador;
    }

    public void setTomaContador(boolean tomaContador) {
        this.tomaContador = tomaContador;
    }
}
