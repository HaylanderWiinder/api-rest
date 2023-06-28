package br.com.alura.forum1.repository;

import br.com.alura.forum1.modelo.Curso;
import br.com.alura.forum1.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
}
