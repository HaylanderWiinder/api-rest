package br.com.alura.forum1.controller;

import br.com.alura.forum1.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum1.controller.dto.TopicoDto;
import br.com.alura.forum1.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum1.controller.form.TopicoForm;
import br.com.alura.forum1.modelo.Curso;
import br.com.alura.forum1.modelo.Topico;
import br.com.alura.forum1.repository.CursoRepository;
import br.com.alura.forum1.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;
    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        if (nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        }else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }
    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }
    @GetMapping("/{id}")
    public ResponseEntity <DetalhesDoTopicoDto> detalhar(@PathVariable Long id){
       Optional <Topico> topico = topicoRepository.findById(id);
       if (topico.isPresent()) {
           return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
       }

       return ResponseEntity.notFound().build();
    }
    @PutMapping("/{}id")
    @Transactional
    public  ResponseEntity<TopicoDto> atualizar(@PathVariable Long id,@RequestBody @Valid AtualizacaoTopicoForm form ){
        Optional <Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico =  form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> remover(@PathVariable Long id){
        Optional <Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
