package com.documax.dot.domain.dto;

import com.documax.dot.domain.cliente.Cliente;

public record DatosListadoClientes(
        Long id,
        String identificacion,
        String nombre
) {
    public DatosListadoClientes(Cliente cliente){
        this(cliente.getId(),cliente.getIdentificacion(),cliente.getNombre());
    }
}
