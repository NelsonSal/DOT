package com.documax.dot.controller;

import com.documax.dot.repository.ClienteRepository;
import com.documax.dot.domain.cliente.Cliente;
import com.documax.dot.domain.dto.DatosRegistroCliente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
//@RequiredArgsConstructor
public class ClienteController {


    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public void registarMedico(@RequestBody @Valid DatosRegistroCliente datosRegistroCliente) {
        clienteRepository.save(new Cliente(datosRegistroCliente));
    }
}