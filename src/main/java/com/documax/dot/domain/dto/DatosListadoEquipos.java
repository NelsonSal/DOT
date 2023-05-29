package com.documax.dot.domain.dto;

import com.documax.dot.domain.cliente.Cliente;
import com.documax.dot.domain.equipo.Equipo;
import com.documax.dot.domain.equipo.Marca;
import com.documax.dot.domain.equipo.Modelo;
import jakarta.validation.constraints.NotNull;

public record DatosListadoEquipos(
        Long id,
        String serial,
        String marca,
        String modelo,
        String cliente

) {
    public DatosListadoEquipos(Equipo equipo){
        this(equipo.getId(), equipo.getSerial(), equipo.getMarca().name(),
                equipo.getModelo().name(),equipo.getCliente().getNombre());

    }
}
