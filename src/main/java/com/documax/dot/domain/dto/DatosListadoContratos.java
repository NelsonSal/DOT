package com.documax.dot.domain.dto;

import com.documax.dot.domain.contrato.Contrato;
import com.documax.dot.domain.equipo.Equipo;

import java.util.Date;

public record DatosListadoContratos(
        Long id1,
        Long id,
        String refContrato,
        Date fechaContrato,
        String cliente,
        Long cupoPaginas,
        Long costoPagina,
        Long costoPaginaAdicional,
        Long cupoScan,
        Long costoScanAdicional,
        String detalles,
        String equipo
) {
    public DatosListadoContratos(Contrato contrato){
        this(contrato.getEquipo().getId(),contrato.getId(), contrato.getRefContrato(), contrato.getFechaContrato(),contrato.getEquipo().getCliente().getNombre(),
                contrato.getCupoPaginas(),
                contrato.getCostoPagina(),contrato.getCostoPaginaAdicional(),contrato.getCupoScan(),
                contrato.getCostoScanAdicional(),contrato.getDetalles(),contrato.getEquipo().getModelo().name()
                );
    }
}
