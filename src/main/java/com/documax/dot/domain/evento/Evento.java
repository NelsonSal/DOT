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

    private Long tipoEvento; //1: Toma contadores 2: Otro

    private String detalle;

    public Evento() {
    }

    public Evento(Long id, Equipo equipo, Date fechaEvento, Long contadorTotal, Long tipoEvento, String detalle) {
        this.id = id;
        this.equipo = equipo;
        this.fechaEvento = fechaEvento;
        this.contadorTotal = contadorTotal;
        this.tipoEvento = tipoEvento;
        this.detalle = detalle;
    }
    public Evento( Date fechaEvento, Long contadorTotal, Long tipoEvento, String detalle) {
        this.fechaEvento = fechaEvento;
        this.contadorTotal = contadorTotal;
        this.tipoEvento = tipoEvento;
        this.detalle = detalle;
    }

    public Evento(DatosRegistroEvento datosRegistroEvento) {
        //this.id = id;
        this.equipo = datosRegistroEvento.equipo();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fechaEvento = (formatter.parse(datosRegistroEvento.fechaEvento()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //this.fechaEvento = datosRegistroEvento.fechaEvento();
        this.contadorTotal = datosRegistroEvento.contadorTotal();
        this.tipoEvento = datosRegistroEvento.tipoEvento();
        this.detalle = datosRegistroEvento.detalle();
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

    public Long getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Long tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
