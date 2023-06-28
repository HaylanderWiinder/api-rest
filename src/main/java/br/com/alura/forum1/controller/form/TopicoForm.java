package br.com.alura.forum1.controller.form;

import br.com.alura.forum1.modelo.Curso;
import br.com.alura.forum1.modelo.Topico;
import br.com.alura.forum1.repository.CursoRepository;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;

public class TopicoForm {
    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String meensagem;
    @NotNull @NotEmpty
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMeensagem() {
        return meensagem;
    }

    public void setMeensagem(String meensagem) {
        this.meensagem = meensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, meensagem,curso);
    }
}
