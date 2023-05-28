package com.documax.dot.domain.cliente;
import com.documax.dot.domain.dto.DatosRegistroCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="clientes")
@Entity(name="Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long identificacion;
    private String nombre;

    public Cliente(DatosRegistroCliente datosRegistroCliente) {
        this.nombre=datosRegistroCliente.nombre();
        this.identificacion=datosRegistroCliente.identificacion();
    }
}
