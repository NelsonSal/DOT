package com.documax.dot.repository;

import com.documax.dot.domain.evento.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

     Evento findFirstByEquipo_idOrderByFechaEventoDesc(Long id);

     @Query(value="SELECT * FROM eventos  WHERE eventos.equipo_id = ?1 AND id<= ?2 ORDER BY eventos.fecha_evento  DESC LIMIT 2",
             nativeQuery = true)

     List<Evento> buscar(Long id, Long idE);

     List<Evento> findAllByOrderByIdDesc();


     //Para lograr esta consulta primero la ensaye en Workbench pues la consulta SQL pura es diferente

    
}
