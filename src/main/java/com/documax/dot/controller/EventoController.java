package com.documax.dot.controller;

import com.documax.dot.domain.contrato.Contrato;
import com.documax.dot.domain.dto.DatosAgregarEquipo;
import com.documax.dot.domain.dto.DatosListadoEquipos;
import com.documax.dot.domain.dto.DatosListadoEventos;
import com.documax.dot.domain.dto.DatosRegistroEvento;
import com.documax.dot.domain.equipo.Equipo;
import com.documax.dot.domain.evento.Evento;
import com.documax.dot.domain.liquidacion.Liquidacion;
import com.documax.dot.repository.ContratoRepository;
import com.documax.dot.repository.EquipoRepository;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private ContratoRepository contratoRepository;

    @GetMapping("/agregarEvento")
    public String mostrarFormNewEvento(Model model){
        Evento newEvento= new Evento();
        model.addAttribute("newEvento",newEvento);
        return "newEventoForm";
    }

    @PostMapping("/guardarEvento")
    public String guardaNewEvento(@ModelAttribute("newEvento")DatosRegistroEvento datosRegistroEvento) throws ParseException {
        System.out.println("fecha desde formulario= "+datosRegistroEvento.fechaEvento());
        System.out.println(datosRegistroEvento);
        eventoRepository.save(new Evento(datosRegistroEvento));
        //acá podria mandar a hacer la liquidacion en lugar de mostrar el listado de eventos.
        //tocaria llamar al contrato (Metodo?) , y buscar la lectura anterior a la actual.
        //lO VOY A INTENTAR CON UN METODO ACÁ.....
       // pruebaMandarLiquidacion(datosRegistroEvento.equipo().getId()); //= NO funka
        //Probar mandando a metodo en Liquidacion
        //Long id=datosRegistroEvento.equipo().getId();
        //System.out.println("supuesto id=" + id);
        //return "redirect:/agregarLiquidacion/{id}";
        return "redirect:/listadoEventos";
    }

    @GetMapping("/listadoEventos")
    public String listadoEventos(Model model){
        List<DatosListadoEventos> listaEventos= eventoRepository.findAllByOrderByIdDesc().stream().map(DatosListadoEventos::new).toList();
        model.addAttribute("listaEventos",listaEventos);
        return "listaEventos";
    }

    @GetMapping("/buscarUltimaLectura/{id}")
    public String buscarUltimoContador(@PathVariable (value="id") Long equipo_id, Model model){
        System.out.println("entró");
        Evento evento = eventoRepository.findFirstByEquipo_idOrderByFechaEventoDesc(equipo_id);
        System.out.println("Contador traido= "+evento.getContadorTotal()+" en la fecha= "+evento.getFechaEvento());
        System.out.println("Cliente traido= "+evento.getEquipo().getCliente().getNombre()+" en la fecha= "+evento.getFechaEvento());
        model.addAttribute("ultimoEvento",evento);
        //TODO Traer detalles del contrato para mostrar y (calcular liquidación?)
        Contrato contrato = contratoRepository.findByEquipo_id(equipo_id);
        System.out.println("Contrato traido: "+ contrato.getRefContrato());
        model.addAttribute("contrato",contrato);
        Evento nuevoEvento= new Evento();
        model.addAttribute("newEvento", nuevoEvento);
        return "nuevaTomaContador";

    }
    public String mandoDatos(Model model , Evento evento){
        model.addAttribute("ultimoEvento",evento);
        Evento nuevoEvento= new Evento();
        model.addAttribute("nuevoEvento", nuevoEvento);
        return "nuevaTomaContador";
    }

    @PostMapping("/guardarEvento/{id}")
    public String guardaNewEventoPorId(@ModelAttribute("newEvento")DatosRegistroEvento datosRegistroEvento,@PathVariable (value="id") Long equipo_idd)  {
        //System.out.println("fecha desde formulario= " + datosRegistroEvento.fechaEvento());
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Long id5=equipo_idd;
        Optional<Equipo> equipo = equipoRepository.findById(equipo_idd);
        System.out.println(datosRegistroEvento);
        Evento evento= new Evento();

        //System.out.println(evento);
        //eventoRepository.save(new Evento(datosRegistroEvento));
        return "redirect:/listadoEventos";
    }

    public String pruebaMandarLiquidacion( long id, Model model){
        System.out.println("Entró y procesa, Equipo ID= "+id);
        //llamar las dos ultimas lecturas de contadores para ese equipo id
        List<Evento> listaEventos= eventoRepository.buscar(id, id);
        System.out.println(listaEventos);
        Evento eventoAnterior = new Evento();
        eventoAnterior= listaEventos.get(1);
        Evento eventoActual = new Evento();
        eventoActual= listaEventos.get(0);
        System.out.println("Evento 0= "+ eventoActual.getContadorTotal());
        System.out.println("Evento 1= "+ eventoAnterior.getContadorTotal());
        //lLAMAR DATOS DE CONTRATO
        Contrato contrato = contratoRepository.findByEquipo_id(id);
        System.out.println("Contrato traido: "+ contrato.getRefContrato());
        //calcular y asignar datos a liquidacion
        Liquidacion liquidacion = new Liquidacion();
        //Calcular numero de dias:
        //liquidacion.setFechaInicio(eventoAnterior.getFechaEvento());
        //liquidacion.setFechaFinal(eventoActual.getFechaEvento());
        long fechaInicialMs = (eventoAnterior.getFechaEvento()).getTime();
        long fechaFinalMs =(eventoActual.getFechaEvento()).getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        int dias = Math.round(diferencia / (1000 * 60 * 60 * 24));
        liquidacion.setDiasTotales(dias);
        System.out.println("Numero de dias= "+liquidacion.getDiasTotales());
        //Calcular paginas Totales
        liquidacion.setTotalPaginas(eventoActual.getContadorTotal()-eventoAnterior.getContadorTotal());
        System.out.println("Total paginas= "+liquidacion.getTotalPaginas());
        //Calcular scans adicionales
        liquidacion.setTotalScan(eventoActual.getContadorScan()-eventoAnterior.getContadorScan());
        //Liquidacion----->
        if (liquidacion.getTotalPaginas()<=contrato.getCupoPaginas()){
            // liquidacion.setNota1("Se factura basico Mensual");
            liquidacion.setTotalLiquidacion(contrato.getCargoBasico());
            System.out.println("Total a Pagar es: "+liquidacion.getTotalLiquidacion());
        }
        else{
            liquidacion.setPagAdicionales(liquidacion.getTotalPaginas()-contrato.getCupoPaginas());
            liquidacion.setCostoPagAdicionales(liquidacion.getPagAdicionales()*contrato.getCostoPaginaAdicional());
            liquidacion.setTotalLiquidacion((contrato.getCargoBasico())+liquidacion.getCostoPagAdicionales());
        }
        if (liquidacion.getTotalScan()<= contrato.getCupoScan()){
            liquidacion.setScanAdicionales(0l);
            liquidacion.setCostoScanAdicional(0l);
        } else {
            liquidacion.setScanAdicionales(liquidacion.getTotalScan()-contrato.getCupoScan());
            liquidacion.setCostoScanAdicional(liquidacion.getScanAdicionales()*contrato.getCostoScanAdicional());
            if (eventoActual.isChargeScan()){
                liquidacion.setTotalLiquidacion(liquidacion.getTotalLiquidacion()+liquidacion.getCostoScanAdicional());
            } else{
                liquidacion.setNota2("No se facturan "+ liquidacion.getScanAdicionales()+ " Scans por $"+ liquidacion.getCostoScanAdicional());
            }
        }

        if (liquidacion.getTotalLiquidacion()==contrato.getCargoBasico()){
            liquidacion.setNota1("Se factura Cargo Básico Mensual");
        }


        //Calcular cargo basico segun contrato
        //liquidacion.setCargoBasico(contrato.getCostoPagina()*contrato.getCupoPaginas());
        //Si numero de paginas es menor a minimo, facturar basico. Si no, calcular paginas/costo extra



        System.out.println("Total a Pagar es: "+liquidacion.getTotalLiquidacion());

        model.addAttribute("ultimoEvento",eventoAnterior);
        model.addAttribute("actualEvento",eventoActual);
        model.addAttribute("contrato",contrato);
        model.addAttribute("liquidacion",liquidacion);

        return "generarReporte";
    }


}
