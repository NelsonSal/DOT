package com.documax.dot.domain.dto;

import com.documax.dot.domain.equipo.Equipo;

import java.util.Date;

public record DatosRegistroLiquidacion(
        Long id,
        Equipo equipo,
        long cargoBasico,
        Date fechaInicio,
        Date fechaFinal,
        long diasTotales,
        long contadorPagInicial,
        long contadorPagFinal,
        long contadorScanInicial,
        long contadorScanFinal,
        long pagAdicionales,
        long scanAdicionales,
        boolean pagoMinimo,
        long costoPagAdicionales,
        long costoScanAdicional,
        long totalLiquidacion,
        String nota1,
        String nota2) {
}
