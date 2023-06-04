package com.documax.dot.domain.dto;

import com.documax.dot.domain.equipo.Equipo;
import com.documax.dot.domain.equipo.Modelo;
import com.documax.dot.domain.evento.Evento;

import java.util.Date;

public record DatosListadoEventos(
        Long id,
        String cliente,
        String marca,
        String modelo,
        Date fechaEvento,
        Long contadorTotal,
        Long tipoEvento,
        String detalle) {
    public DatosListadoEventos(Evento evento){
        this(evento.getId(),evento.getEquipo().getCliente().getNombre(),evento.getEquipo().getMarca().name(),evento.getEquipo().getModelo().name(),evento.getFechaEvento(),evento.getContadorTotal(),evento.getTipoEvento(),
                evento.getDetalle());

    }
}
