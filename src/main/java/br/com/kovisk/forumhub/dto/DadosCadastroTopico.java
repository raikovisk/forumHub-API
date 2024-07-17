package br.com.kovisk.forumhub.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Valid
public record DadosCadastroTopico(@NotBlank String titulo,
                                  @NotBlank String mensagem,
                                  @NotBlank String autor,
                                  @NotBlank String curso) {

}
