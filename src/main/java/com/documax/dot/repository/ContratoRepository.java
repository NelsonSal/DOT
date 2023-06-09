package com.documax.dot.repository;

import com.documax.dot.domain.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}
