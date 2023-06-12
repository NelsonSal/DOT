package com.documax.dot.domain.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCliente( String identificacion, String nombre) {
}
