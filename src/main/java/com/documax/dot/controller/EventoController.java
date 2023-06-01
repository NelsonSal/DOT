package com.documax.dot.controller;

import com.documax.dot.domain.dto.DatosAgregarEquipo;
import com.documax.dot.domain.dto.DatosRegistroEvento;
import com.documax.dot.domain.evento.Evento;
import com.documax.dot.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/agregarEvento")
    public String mostrarFormNewEvento(Model model){
        Evento newEvento= new Evento();
        model.addAttribute("newEvento",newEvento);
        return "newEventoForm";
    }

    @PostMapping("/guardarEvento")
    public String guardaNewEvento(@ModelAttribute("newEvento")DatosRegistroEvento datosRegistroEvento){
        System.out.println(datosRegistroEvento);
        return "fin";
    }
}
