package pessoa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@ApplicationScoped
public class IPessoaRepository implements PanacheRepository<Pessoa> {

    //deixei uma consulta apenas para me apresentar, o resto fiz em spring e hibernate puro para mostrar o que sei

    public ArrayList<String> pessoas(String nome) {
        return (ArrayList) find("Select a.id, a.nome, a.departamento "
                + "from Pessoa a where a.nome = ?1", nome).list();
    }

//    public (ArrayList) AbstractList somatorioTarefas(Long id) {
//        return  (ArrayList) find("Select SUM(a.duracao) "
//                + "from Tarefa a where a.status like 'Finalizado' and a.id = ?1", id).list();
//    }

//    public Integer gastos(String nome) {
//        return list("Select AVG(a.tarefas.duracao) from a where a.nome = ?1", nome);
//    }

//    public Pessoa departamentos() {
//        return (Pessoa) find("Select a.departamento, "
//                + "(Select COUNT(b.id) from Pessoa b where b.departamento = a.departamento) as qtdPessoas "
//                + "(Select COUNT(c.id) from Tarefa c where c.pessoa.departamento = a.departamento) as qtdTarefas"
//                + "from a "
//                + "group by a.departamento", "");
//    }
}
