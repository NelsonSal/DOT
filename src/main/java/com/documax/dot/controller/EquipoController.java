package com.documax.dot.controller;

import com.documax.dot.domain.dto.DatosAgregarEquipo;
import com.documax.dot.domain.dto.DatosListadoEquipos;
import com.documax.dot.domain.equipo.Equipo;
import com.documax.dot.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/equipos")
@Controller
//@RequestMapping
public class EquipoController {

    @Autowired
    private  EquipoRepository equipoRepository;
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
        return "listado";
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
        equipoRepository.save(new Equipo(datosAgregarEquipo));
        return "listado";
    }

}
