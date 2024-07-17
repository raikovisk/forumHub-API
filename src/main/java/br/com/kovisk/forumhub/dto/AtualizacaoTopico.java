package br.com.kovisk.forumhub.dto;

import br.com.kovisk.forumhub.model.Topico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Valid
public record AtualizacaoTopico(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        String curso
    ) {
    public AtualizacaoTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(),topico.getMensagem(), topico.getCurso());
    }
}
