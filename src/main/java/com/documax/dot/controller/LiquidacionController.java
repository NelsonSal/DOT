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

    @GetMapping("/agregarLiquidacion/{id}/{idE}")
    public String mostrarDatosParaLiquidacion(@PathVariable(value="id") Long equipo_id, @PathVariable(value="idE") Long id,Model model){
        //llamar las dos ultimas lecturas de contadores para ese equipo id
        List<Evento> listaEventos= eventoRepository.buscar(equipo_id, id);
        System.out.println(listaEventos);
        Evento eventoAnterior = listaEventos.get(1);
        Evento eventoActual = listaEventos.get(0);
        System.out.println("Evento 0= "+ eventoActual.getContadorTotal());
        System.out.println("Evento 1= "+ eventoAnterior.getContadorTotal());
        //lLAMAR DATOS DE CONTRATO
        Contrato contrato = contratoRepository.findByEquipo_id(equipo_id);
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
        liquidacion.setIdEventoAnt(eventoAnterior.getId());
        liquidacion.setIdEventoAct(eventoActual.getId());
        liquidacion.setEquipo(contrato.getEquipo());
        liquidacionRepository.save(liquidacion);

        model.addAttribute("ultimoEvento",eventoAnterior);
        model.addAttribute("actualEvento",eventoActual);
        model.addAttribute("contrato",contrato);
        model.addAttribute("liquidacion",liquidacion);

        return "generarReporteHtml";
    }

    @GetMapping("/deleteLiquidacion/{id}")
    public String deleteLiquidacion(@PathVariable (value="id") Long id, Model model){
        liquidacionRepository.deleteById(id);
        return "redirect:/listadoEventos";
    }


}
