package com.documax.dot.repository;

import com.documax.dot.domain.evento.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

     Evento findFirstByEquipo_idOrderByFechaEventoDesc(Long id);

}
