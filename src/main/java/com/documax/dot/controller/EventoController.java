package com.documax.dot.controller;

import com.documax.dot.domain.dto.DatosAgregarEquipo;
import com.documax.dot.domain.dto.DatosListadoEquipos;
import com.documax.dot.domain.dto.DatosListadoEventos;
import com.documax.dot.domain.dto.DatosRegistroEvento;
import com.documax.dot.domain.evento.Evento;
import com.documax.dot.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
    public String guardaNewEvento(@ModelAttribute("newEvento")DatosRegistroEvento datosRegistroEvento) throws ParseException {
        System.out.println("fecha desde formulario= "+datosRegistroEvento.fechaEvento());
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        eventoRepository.save(new Evento(datosRegistroEvento));
        return "redirect:/listadoEventos";
    }

    @GetMapping("/listadoEventos")
    public String listadoEventos(Model model){
        List<DatosListadoEventos> listaEventos= eventoRepository.findAll().stream().map(DatosListadoEventos::new).toList();
        model.addAttribute("listaEventos",listaEventos);
        return "listaEventos";
    }

    @GetMapping("/buscarUltimaLectura/{id}")
    public String  buscarUltimoContador(@PathVariable (value="id") Long equipo_id){
        System.out.println("entró");
        Evento evento;
        evento = eventoRepository.findFirstByEquipo_idOrderByFechaEventoDesc(equipo_id);
        System.out.println("Contador traido= "+evento.getContadorTotal()+" en la fecha= "+evento.getFechaEvento());
        return "listaEventos";
    }
}
