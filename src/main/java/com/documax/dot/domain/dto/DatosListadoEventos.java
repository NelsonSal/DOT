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
        Long contadorScan,
        Boolean tomaContador ,
        String detalle,
        Long equipo_id) {
    public DatosListadoEventos(Evento evento){
        this(evento.getId(),evento.getEquipo().getCliente().getNombre(),evento.getEquipo().getMarca().name(),evento.getEquipo().getModelo().name(),evento.getFechaEvento(),evento.getContadorTotal(), evento.getContadorScan(),evento.isTomaContador(),
                evento.getDetalle(), evento.getEquipo().getId());

    }
}
