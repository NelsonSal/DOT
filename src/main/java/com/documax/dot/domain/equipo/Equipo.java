package com.documax.dot.domain.equipo;

import com.documax.dot.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="equipos")
@Entity(name="Equipo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String serial;
    @Enumerated(EnumType.STRING)
    private Marca marca;
    @Enumerated(EnumType.STRING)
    private Modelo modelo;
    @OneToOne
    private Cliente cliente;
}
