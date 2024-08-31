package pessoa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import tarefa.Tarefa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class Pessoa extends PanacheEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name ="id_Tarefa", nullable = true)
    private List<Tarefa> tarefas;

    private Integer soma;

    private Long qtdPessoa;

    private Long qtdTarefa;

    public Pessoa() {}

    public Pessoa(String nome, String departamento, Integer soma) {
        this.nome = nome;
        this.departamento = departamento;
        this.soma = soma;
    }

    public Integer getSoma() {
        return soma;
    }

    public void setSoma(Integer soma) {
        this.soma = soma;
    }

    public Long getQtdPessoa() {
        return qtdPessoa;
    }

    public void setQtdPessoa(Long qtdPessoa) {
        this.qtdPessoa = qtdPessoa;
    }

    public Long getQtdTarefa() {
        return qtdTarefa;
    }

    public void setQtdTarefa(Long qtdTarefa) {
        this.qtdTarefa = qtdTarefa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

}
