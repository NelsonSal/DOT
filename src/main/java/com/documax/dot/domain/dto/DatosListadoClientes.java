package com.documax.dot.domain.dto;

import com.documax.dot.domain.cliente.Cliente;

public record DatosListadoClientes(
        Long identificacion,
        String nombre
) {
    public DatosListadoClientes(Cliente cliente){
        this(cliente.getIdentificacion(),cliente.getNombre());
    }
}
