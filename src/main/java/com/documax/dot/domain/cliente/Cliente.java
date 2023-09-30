package com.documax.dot.domain.cliente;
import com.documax.dot.domain.dto.DatosRegistroCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="clientes")
@Entity(name="Cliente")

@EqualsAndHashCode(of="identificacion")
public class Cliente {
    @Id
    private Long identificacion;
    private String nombre;

    public Cliente() {


    }


    public Cliente(Long identificacion, String nombre) {
        this.identificacion = identificacion;
        this.nombre = nombre;
    }



    public Cliente(DatosRegistroCliente datosRegistroCliente) {
        this.nombre=datosRegistroCliente.nombre();
        this.identificacion=datosRegistroCliente.identificacion();
    }



    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
