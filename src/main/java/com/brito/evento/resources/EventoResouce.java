package com.brito.evento.resources;

import com.brito.evento.models.Evento;
import com.brito.evento.service.EventoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value ="API REST EVENTOS")
@RestController
@RequestMapping("/evento")
public class EventoResouce {

    @Autowired
    private EventoService eventoService;

    @ApiOperation(value = "Retorna lista de eventos")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Evento> listaEvento(){
        return eventoService.listarEvento();
    }

    @ApiOperation(value = "Busca um evento")
    @GetMapping(value = "{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Evento> buscaEvento(@PathVariable Long codigo){
        Evento evento = eventoService.buscarEvento(codigo);
        return ResponseEntity.ok(evento);
    }

    @ApiOperation(value = "Cadastra um evento")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Evento> cadastraEvento(@RequestBody @Valid Evento evento){
        Evento eventoSalvo = eventoService.salvar(evento);
        return ResponseEntity.ok(eventoSalvo);
    }

    @ApiOperation(value = "Altera um evento")
    @PutMapping(value = "{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Evento> alteraEvento(@RequestBody Evento evento, @PathVariable Long codigo){
        try {
            eventoService.atualizarEvento(evento, codigo);
            return ResponseEntity.ok(evento);
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Exclui um evento")
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
