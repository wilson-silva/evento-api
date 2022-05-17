package com.brito.evento.service;

import com.brito.evento.models.Evento;
import com.brito.evento.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento salvar(Evento evento){
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEvento(){
        return eventoRepository.findAll();
    }

    public Evento buscarEvento(Long codigo){
        Optional<Evento> evento = eventoRepository.findById(codigo);
        return evento.orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
    }


    public Evento atualizarEvento(Evento evento, Long codigo){
        Evento eventoOriginal = this.buscarEvento(codigo);
        evento.setCodigo(eventoOriginal.getCodigo());
        return eventoRepository.save(evento);
    }

    public void excluirEvento(Long codigo){
        Evento eventoOriginal = this.buscarEvento(codigo);
        eventoRepository.delete(eventoOriginal);
    }


}
