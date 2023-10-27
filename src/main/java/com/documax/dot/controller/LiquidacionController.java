package com.documax.dot.controller;

import com.documax.dot.domain.contrato.Contrato;
import com.documax.dot.domain.dto.DatosListadoEventos;
import com.documax.dot.domain.evento.Evento;
import com.documax.dot.domain.liquidacion.Liquidacion;
import com.documax.dot.repository.ContratoRepository;
import com.documax.dot.repository.EventoRepository;
import com.documax.dot.repository.LiquidacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.temporal.Temporal;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class LiquidacionController {

    @Autowired
    private LiquidacionRepository liquidacionRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ContratoRepository contratoRepository;

    @GetMapping("/agregarLiquidacion/{id}")
    public String mostrarDatosParaLiquidacion(@PathVariable(value="id") Long equipo_id, Model model){
        //llamar las dos ultimas lecturas de contadores para ese equipo id
        List<Evento> listaEventos= eventoRepository.buscar(equipo_id);
        System.out.println(listaEventos);
        Evento evento1 = new Evento();
        evento1= listaEventos.get(1);
        Evento evento0 = new Evento();
        evento0= listaEventos.get(0);
        System.out.println("Evento 0= "+ evento0.getContadorTotal());
        System.out.println("Evento 1= "+ evento1.getContadorTotal());
        //lLAMAR DATOS DE CONTRATO
        Contrato contrato = contratoRepository.findByEquipo_id(equipo_id);
        System.out.println("Contrato traido: "+ contrato.getRefContrato());
        //calcular y asignar datos a liquidacion
        Liquidacion liquidacion = new Liquidacion();
            //Calcular paginas Totales
        liquidacion.setContadorPagInicial(evento1.getContadorTotal());
        liquidacion.setContadorPagFinal(evento0.getContadorTotal());
        liquidacion.setTotalPaginas(evento0.getContadorTotal()-evento1.getContadorTotal());
        System.out.println("Total paginas= "+liquidacion.getTotalPaginas());
            //Calcular numero de dias:
        liquidacion.setFechaInicio(evento1.getFechaEvento());
        liquidacion.setFechaFinal(evento0.getFechaEvento());
        long fechaInicialMs = (evento1.getFechaEvento()).getTime();
        long fechaFinalMs =(evento0.getFechaEvento()).getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        int dias = Math.round(diferencia / (1000 * 60 * 60 * 24));
        liquidacion.setDiasTotales(dias);
        System.out.println("Numero de dias= "+liquidacion.getDiasTotales());
            //Calcular scans adicionales
        liquidacion.setTotalScan(evento0.getContadorScan()-evento1.getContadorScan());
        if (liquidacion.getTotalScan()<= contrato.getCupoScan()){
            liquidacion.setScanAdicionales(0l);
            liquidacion.setCostoScanAdicional(0l);
        } else {
            liquidacion.setScanAdicionales(liquidacion.getTotalScan()-contrato.getCupoScan());
            liquidacion.setCostoScanAdicional(liquidacion.getScanAdicionales()*contrato.getCostoScanAdicional());
        }


            //Calcular cargo basico segun contrato
        liquidacion.setCargoBasico(contrato.getCostoPagina()*contrato.getCupoPaginas());
            //Si numero de paginas es menor a minimo, facturar basico. Si no, calcular paginas/costo extra
        if (liquidacion.getTotalPaginas()<=contrato.getCupoPaginas()){
            liquidacion.setNota1("Se factura basico Mensual");
            liquidacion.setTotalLiquidacion(contrato.getCostoPagina()*contrato.getCupoPaginas());
            System.out.println("Total a Pagar es: "+liquidacion.getTotalLiquidacion());
            }
        else{
            liquidacion.setPagAdicionales(liquidacion.getTotalPaginas()-contrato.getCupoPaginas());
            liquidacion.setCostoPagAdicionales(liquidacion.getPagAdicionales()*contrato.getCostoPaginaAdicional());
            liquidacion.setTotalLiquidacion((contrato.getCostoPagina()*contrato.getCupoPaginas())+liquidacion.getCostoPagAdicionales());
        }


        System.out.println("Total a Pagar es: "+liquidacion.getTotalLiquidacion());

        model.addAttribute("ultimoEvento",evento1);
        model.addAttribute("actualEvento",evento0);
        model.addAttribute("contrato",contrato);
        model.addAttribute("liquidacion",liquidacion);

        return "generarReporte";
    }


}
