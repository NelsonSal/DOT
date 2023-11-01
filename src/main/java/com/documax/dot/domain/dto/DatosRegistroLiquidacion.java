package com.documax.dot.domain.dto;

import com.documax.dot.domain.equipo.Equipo;

import java.util.Date;

public record DatosRegistroLiquidacion(
         Equipo equipo,
         long idEventoAnt,
         long idEventoAct,
         long diasTotales,
         long totalPaginas,
         long totalScan,
         long pagAdicionales,
         long scanAdicionales,
         boolean pagoMinimo,
         long costoPagAdicionales,
         long costoScanAdicional,
         long totalLiquidacion,
         String nota1,
         String nota2) {
}
