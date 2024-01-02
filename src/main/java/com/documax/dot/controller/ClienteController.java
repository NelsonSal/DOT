package com.documax.dot.controller;

import com.documax.dot.domain.dto.DatosListadoClientes;
import com.documax.dot.domain.dto.DatosListadoEventos;
import com.documax.dot.repository.ClienteRepository;
import com.documax.dot.domain.cliente.Cliente;
import com.documax.dot.domain.dto.DatosRegistroCliente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClienteController {


    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/agregarCliente")
    public String mostrarFormNewCliente(Model model){
        Cliente newCliente= new Cliente();
        model.addAttribute("cliente",newCliente);
        return "newClienteForm";
    }


    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute ("cliente") DatosRegistroCliente datosRegistroCliente) {
        clienteRepository.save(new Cliente(datosRegistroCliente));
        return "redirect:/listadoClientes";
    }
    @GetMapping("/listadoClientes")
    public String listadoClientes(Model model){
        List<DatosListadoClientes> listaClientes =clienteRepository.findAll().stream().map(DatosListadoClientes::new).toList();
        model.addAttribute("listaClientes",listaClientes);
        return "listaClientes";
    }
}