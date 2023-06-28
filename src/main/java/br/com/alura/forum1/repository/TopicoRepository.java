package br.com.alura.forum1.repository;

import br.com.alura.forum1.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String nomeCurso);
}
