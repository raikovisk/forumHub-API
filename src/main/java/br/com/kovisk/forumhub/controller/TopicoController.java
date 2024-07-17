package br.com.kovisk.forumhub.controller;

import br.com.kovisk.forumhub.dto.AtualizacaoTopico;
import br.com.kovisk.forumhub.dto.DadosCadastroTopico;
import br.com.kovisk.forumhub.model.DadosTopico;
import br.com.kovisk.forumhub.model.Topico;
import br.com.kovisk.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repositorio;

    @PostMapping
    @Transactional
    public void cadastroTopico(@RequestBody @Valid DadosCadastroTopico dados){

        repositorio.save(new Topico(dados));
    }

    @Cacheable(value = "listaTopicos")
    @GetMapping
    public Page<DadosTopico> obterTopicos(@PageableDefault(page = 0, size = 10, sort = "dataCriacao", direction = Sort.Direction.DESC)Pageable paginacao){
        return repositorio.findAll(paginacao)
                .map(t -> new DadosTopico(t.getId(), t.getTitulo(), t.getAutor(), t.getMensagem(),t.getCurso(),t.getDataCriacao(), t.getStatus()));
    }

    @PutMapping("/{topicoId}")
    @Transactional
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<AtualizacaoTopico> atualizaTopico(@PathVariable Long topicoId, @RequestBody @Valid Topico topicoForm) {
        Optional<Topico> optional = repositorio.findById(topicoId);
        if(optional.isPresent()) {
            Topico topico = topicoForm;
            return ResponseEntity.ok(new AtualizacaoTopico(optional.get()));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{topicoId}")
    @Transactional
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long topicoId) {

        Optional<Topico> optional = repositorio.findById(topicoId);

        if(optional.isPresent()) {
            repositorio.deleteById(topicoId);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }


}
