package br.com.kovisk.forumhub.model;

import java.time.LocalDateTime;

public record DadosTopico(Long id,
                          String titulo,
                          String autor,
                          String mensagem,
                          String curso,
                          StatusTopico status,
                          LocalDateTime dataCriacao){


    public DadosTopico(Long id, String titulo, String autor, String mensagem, String curso, LocalDateTime dataCriacao, StatusTopico status) {
        this(id, titulo, autor, mensagem, curso, status, dataCriacao);
    }
}
