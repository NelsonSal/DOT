package com.documax.dot.controller;

import com.documax.dot.domain.dto.DatosListadoEquipos;
import com.documax.dot.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/equipos")
@Controller
public class EquipoController {

//    @Autowired
    private  EquipoRepository equipoRepository;
    public EquipoController(EquipoRepository equipoRepository){
        this.equipoRepository=equipoRepository;
    }

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
}
