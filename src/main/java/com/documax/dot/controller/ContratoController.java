package com.documax.dot.controller;

import com.documax.dot.domain.contrato.Contrato;
import com.documax.dot.domain.dto.DatosListadoContratos;
import com.documax.dot.domain.dto.DatosListadoEventos;
import com.documax.dot.domain.dto.DatosRegistroContrato;
import com.documax.dot.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContratoController {
    @Autowired
    private ContratoRepository contratoRepository;

    @GetMapping("/nuevoContrato")
    public String mostrarFormNewContrato (Model model){
        Contrato newContrato = new Contrato();
        model.addAttribute("newContrato",newContrato);
        return "newContratoForm";
    }
    @PostMapping("/guardarContrato")
    public String guardarNewContrato(@ModelAttribute ("newContrato")DatosRegistroContrato datosRegistroContrato){
        contratoRepository.save(new Contrato(datosRegistroContrato));
        return "redirect:/listadoContratos";
    }

    @GetMapping("/listadoContratos")
    public String listadoContratos(Model model){
        List<DatosListadoContratos> listaContratos=contratoRepository.findAll().stream().map(DatosListadoContratos::new).toList();
        model.addAttribute("listaContratos",listaContratos);
        return "/listaContratos";

    }
}
