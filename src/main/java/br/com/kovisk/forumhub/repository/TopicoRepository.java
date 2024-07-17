package br.com.kovisk.forumhub.repository;

import br.com.kovisk.forumhub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
