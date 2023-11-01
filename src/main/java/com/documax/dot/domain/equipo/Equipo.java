package com.documax.dot.domain.equipo;

import com.documax.dot.domain.cliente.Cliente;
import com.documax.dot.domain.contrato.Contrato;
import com.documax.dot.domain.dto.DatosAgregarEquipo;
import com.documax.dot.domain.dto.DatosListadoEquipos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="equipos")
@Entity(name="Equipo")
@EqualsAndHashCode(of="id")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serial;
    @Enumerated(EnumType.STRING)
    private Marca marca;
    @Enumerated(EnumType.STRING)
    private Modelo modelo;
    @ManyToOne
    private Cliente cliente;

    public Equipo() {
    }
    public Equipo(DatosAgregarEquipo datosAgregarEquipo){
        this.serial=datosAgregarEquipo.serial();
        this.marca=datosAgregarEquipo.marca();
        this.modelo=datosAgregarEquipo.modelo();
        this.cliente=datosAgregarEquipo.cliente();
        //this.contrato=datosAgregarEquipo.contrato();


    }

//    private Cliente clienteIni() {
//        Cliente cliente = new Cliente(1l);
//        return cliente;
//    }

    public Equipo(Long id, String serial, Marca marca, Modelo modelo, Cliente cliente) {
        this.id = id;
        this.serial = serial;
        this.marca = marca;
        this.modelo = modelo;
        this.cliente = cliente;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
