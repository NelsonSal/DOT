package com.documax.dot.controller;

import com.documax.dot.domain.dto.DatosAgregarEquipo;
import com.documax.dot.domain.dto.DatosListadoEquipos;
import com.documax.dot.domain.equipo.Equipo;
import com.documax.dot.domain.evento.Evento;
import com.documax.dot.repository.EquipoRepository;
import com.documax.dot.repository.EventoRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//@RestController
//@RequestMapping("/equipos")
@Controller
//@RequestMapping
public class EquipoController {

    @Autowired
    private  EquipoRepository equipoRepository;
    @Autowired
    private EventoRepository eventoRepository;

//    @Autowired
//    private final DatosAgregarEquipo datosAgregarEquipo(
//            this.datosAgregarEquipo=datosAgregarEquipo;
//    );

   // public EquipoController(EquipoRepository equipoRepository){
//        this.equipoRepository=equipoRepository;
//    }

//    @GetMapping
//    public List<DatosListadoEquipos> listarEquipos(){
//        return equipoRepository.findAll().stream().map(DatosListadoEquipos::new).toList();
//    }
    @GetMapping("/listadoEquipos")
    public String verListadoEquipos(Model model){
        List<DatosListadoEquipos> list = equipoRepository.findAll().stream().map(DatosListadoEquipos::new).toList();
        model.addAttribute("ListaEquipos", list);
        return "listadoEquipos";
    }
    @GetMapping("/agregarEquipo")
    public String mostrarFormaNuevoEquipo (Model model){
       Equipo equipoNew = new Equipo();
        model.addAttribute("equipo",equipoNew);
        return "nuevoEquipoForm";
    }
    @PostMapping("/guardarEquipo")
    public String guardarEquipo(@ModelAttribute ("equipo") DatosAgregarEquipo datosAgregarEquipo){
        System.out.println(datosAgregarEquipo);
        Equipo equipo = new Equipo(datosAgregarEquipo); //Modificación para obtener el id del registro recien creado
        //equipoRepository.save(new Equipo(datosAgregarEquipo));
        equipoRepository.save(equipo); ////Modificación para obtener el id del registro recien creado
        equipoRepository.flush(); //Modificación para obtener el id del registro recien creado. Se necesita?
                                    //verificar pues estamos teniendo problemas (Memory?) en el servidor linux
        long ultimoIdCreado= equipo.getId();
        System.out.println("ultimo =" + ultimoIdCreado);

        //TODO revisar esta seccion, debe poderse hacer mejor
        //Voy a crear el primer evento como Entrada al sistema:
        Evento evento = new Evento();
        evento.setEquipo(equipo);
        Date date = new Date();
        evento.setFechaEvento(date);
        evento.setTipoEvento(1l);
        evento.setDetalle("Creación en el sistema");
        evento.setContadorTotal(0l);
        eventoRepository.save(evento);

        return "redirect:/listadoEquipos";
    }

}
