package com.documax.dot.controller;

import com.documax.dot.domain.dto.DatosListadoEventos;
import com.documax.dot.domain.evento.Evento;
import com.documax.dot.repository.EventoRepository;
import com.documax.dot.repository.LiquidacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class LiquidacionController {

    @Autowired
    private LiquidacionRepository liquidacionRepository;
    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/agregarLiquidacion/{id}")
    public String mostrarDatosParaLiquidacion(@PathVariable(value="id") Long equipo_id, Model model){
        //llamar las dos ultimas lecturas de contadores para ese equipo id
        System.out.println("equipo_id= "+ equipo_id);
        List<Evento> listaEventos= eventoRepository.buscar(equipo_id);
        System.out.println("Entro a Liq Controller");
        System.out.println(listaEventos);
        Evento evento1 = new Evento();
        evento1= listaEventos.get(1);
        Evento evento0 = new Evento();
        evento0= listaEventos.get(0);
        System.out.println("Evento 0= "+ evento0.getContadorTotal());
        System.out.println("Evento 1= "+ evento1.getContadorTotal());
        //lLAMAR DATOS DE CONTRATO
        //calcular y asignar datos a liquidacion

        return "listaEventos";
    }
}
