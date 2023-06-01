package com.documax.dot.domain.dto;

import com.documax.dot.domain.equipo.Equipo;

import java.util.Date;

public record DatosRegistroEvento(
        Equipo equipo,
        Date fechaEvento,
        Long contadorTotal,
        Long tipoEvento,
        String detalle) {
}
