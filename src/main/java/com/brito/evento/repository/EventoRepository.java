package com.brito.evento.repository;

import com.brito.evento.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {


}
