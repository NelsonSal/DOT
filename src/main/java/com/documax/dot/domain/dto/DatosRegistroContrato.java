package com.documax.dot.domain.dto;

import com.documax.dot.domain.equipo.Equipo;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public record DatosRegistroContrato(
         String refContrato,
         String fechaContrato,
         Long cupoPaginas,
         Long costoPagina,
         Long costoPaginaAdicional,
         Long cupoScan,
         Long costoScanAdicional,
         String detalles,
         Equipo equipo
) {
}
