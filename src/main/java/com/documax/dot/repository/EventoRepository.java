package com.documax.dot.repository;

import com.documax.dot.domain.evento.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
