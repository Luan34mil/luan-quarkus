package tarefa;

import io.quarkus.test.junit.QuarkusTest;
import pessoa.Pessoa;
import pessoa.PessoaControlador;

import javax.inject.Inject;

@QuarkusTest
public class TarefaTest {

    // fiz o teste via postman, mas deixei aqui para visualização da tarefa também

    @Inject
    TarefaControlador tarefaControlador;

    @Inject
    PessoaControlador pessoaControlador;

    public void salvarTest() {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("bug");
        tarefa.setDescricao("ti");
        tarefa.setPrazo(20);
        tarefa.setDepartamento("ti");
        tarefa.setStatus("aberto");

        tarefaControlador.salvar(tarefa);
    }

    public void alocarTest() {
        salvarTest();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Luan");
        pessoa.setDepartamento("ti");

        pessoaControlador.salvar(pessoa);
        tarefaControlador.alocar(1L);
    }

    public void finalizarTest() {
        alocarTest();
        tarefaControlador.finalizar(1L);
    }

    public void pendentesTest() {
        finalizarTest();
        tarefaControlador.finalizar(1L);
    }
}
