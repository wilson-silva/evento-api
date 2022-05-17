package com.brito.evento.resources;

import com.brito.evento.models.Evento;
import com.brito.evento.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoResouce {

    @Autowired
    private EventoService eventoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Evento> listaEvento(){
        return eventoService.listarEvento();
    }

    @GetMapping(value = "{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Evento> buscaEvento(@PathVariable Long codigo){
        Evento evento = eventoService.buscarEvento(codigo);
        return ResponseEntity.ok(evento);
    }

    @PostMapping()
    public ResponseEntity<Evento> cadastraEvento(@RequestBody @Valid Evento evento){
        Evento eventoSalvo = eventoService.salvar(evento);
        return ResponseEntity.ok(eventoSalvo);
    }

    @PutMapping(value = "{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Evento> alteraEvento(@RequestBody Evento evento, @PathVariable Long codigo){
        try {
            eventoService.atualizarEvento(evento, codigo);
            return ResponseEntity.ok(evento);
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<Evento> deletaEvento(@PathVariable Long codigo){
        try{
            eventoService.excluirEvento(codigo);
            return ResponseEntity.ok().build();
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

}
