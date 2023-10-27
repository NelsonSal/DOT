package com.documax.dot.domain.dto;

import com.documax.dot.domain.equipo.Equipo;
import com.documax.dot.domain.evento.Evento;

import java.text.SimpleDateFormat;
import java.util.Date;

public record DatosRegistroEvento(
        Equipo equipo,
        String fechaEvento,
        Long contadorTotal,
        Long contadorScan,
        Long tipoEvento,
        String detalle,
        boolean chargeScan) {
}
