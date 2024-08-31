package pessoa;

import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;

@QuarkusTest
public class PessoaTest {

    @Inject
    PessoaControlador pessoaControlador;

    public void testSalvar() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Luan");
        pessoa.setDepartamento("ti");

        pessoaControlador.salvar(pessoa);
    }

    public void testEditar() {
        testSalvar();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Luan");
        pessoa.setDepartamento("adm");

        pessoaControlador.editar(pessoa, 1L);
    }

    public void testDelete() {
        testSalvar();

        pessoaControlador.delete(1L);
    }

    public void testPessoas() {
        testSalvar();

        pessoaControlador.pessoas();
    }

    public void testGastos() {
        testSalvar();

        pessoaControlador.gastos("Luan");
    }

    public void testDepartamento() {
        testSalvar();

        pessoaControlador.departamentos();
    }
}
