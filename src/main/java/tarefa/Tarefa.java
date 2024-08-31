package tarefa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import pessoa.Pessoa;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tarefa")
public class Tarefa  extends PanacheEntity {

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "prazo", nullable = false)
    private Integer prazo;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @Column(name = "duracao", nullable = false)
    private Integer duracao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Pessoa", referencedColumnName = "id", nullable = true)
    @JsonBackReference
    private Pessoa pessoa;

    @Column(name = "status", nullable = true)
    private String status;

    public Tarefa() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(id, tarefa.id) && Objects.equals(titulo, tarefa.titulo) && Objects.equals(descricao, tarefa.descricao) && Objects.equals(duracao, tarefa.duracao) && Objects.equals(pessoa, tarefa.pessoa) && Objects.equals(status, tarefa.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, duracao, pessoa, status);
    }
}
