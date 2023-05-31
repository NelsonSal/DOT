package com.documax.dot.domain.dto;

import com.documax.dot.domain.cliente.Cliente;
import com.documax.dot.domain.equipo.Equipo;
import com.documax.dot.domain.equipo.Marca;
import com.documax.dot.domain.equipo.Modelo;

public record DatosAgregarEquipo(String serial,
                                 Marca marca,
                                 Modelo modelo,
                                 Cliente cliente)
{



}
