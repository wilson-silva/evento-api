package com.brito.evento.resources;

import com.brito.evento.models.Evento;
import com.brito.evento.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/evento")
public class EventoResouce {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Evento> listaEvento(){
        return eventoRepository.findAll();

    }

    @PostMapping()
    public Evento cadastraEvento(@RequestBody @Valid Evento evento){
        return eventoRepository.save(evento);
    }

    @DeleteMapping()
    public Evento deletaEvento(@RequestBody Evento evento){
        eventoRepository.delete(evento);
        return evento;

    }



}
