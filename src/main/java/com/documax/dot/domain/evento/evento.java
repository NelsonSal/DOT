package com.documax.dot.domain.evento;

import com.documax.dot.domain.equipo.Equipo;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Date;
@Table(name="eventos")
@Entity(name="evento")
@EqualsAndHashCode(of="id")
public class evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Equipo equipo;
    private Date fechaEvento;
    private Long contadorTotal;

    private Long tipoEvento; //1: Toma contadores 2: Otro

    public evento() {
    }

    public evento(Long id, Equipo equipo, Date fechaEvento, Long contadorTotal, Long tipoEvento) {
        this.id = id;
        this.equipo = equipo;
        this.fechaEvento = fechaEvento;
        this.contadorTotal = contadorTotal;
        this.tipoEvento = tipoEvento;
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
}
