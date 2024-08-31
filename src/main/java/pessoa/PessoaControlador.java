package pessoa;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import tarefa.ITarefaRepository;
import tarefa.Tarefa;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/pessoas")
public class PessoaControlador {

    @Inject
    IPessoaRepository repository;

    @Inject
    ITarefaRepository repositoryTarefa;

    @POST
    @Transactional
    public void salvar(Pessoa pessoa) {
        repository.persist(pessoa);
    }

    @PUT
    @Transactional
    @Path("/id/{id}")
    public void editar(Pessoa pessoa, @PathParam() Long id) {
        pessoa.setId(id);
        repository.persist(pessoa);
    }

    @DELETE
    @Transactional
    @Path("/id/{id}")
    public void delete(@PathParam Long id) {
        repository.deleteById(id);;
    }

    @GET
    @Transactional
    public List<Pessoa> pessoas() {
        List<Pessoa> pessoas = repository.listAll();

        // criaria uma consulta no repositorio e um construtor na entidade
        for (Pessoa pessoa : pessoas) {
            pessoa.setSoma(pessoa.getTarefas().stream().filter(x -> x.getStatus().equals("Finalizado")).mapToInt(Tarefa::getDuracao)
                    .sum());
        }

       return pessoas;
    }

    @GET
    @Transactional
    @Path("/gastos/nome/{nome}")
    public OptionalDouble gastos(@PathParam String nome) {

        //criaria uma consulta simples no repositorio
        Pessoa pessoa = repository.listAll().stream().filter(x -> x.getNome().equals(nome)).collect(Collectors.toList()).get(0);

        return pessoa.getTarefas().stream().mapToInt(Tarefa::getDuracao)
                .average();
    }

    @GET
    @Transactional
    @Path("/departamentos")
    public List<Pessoa> departamentos() {

        // aqui faria um sql com union all entra as tabela para tirar
        // os departamento e um group by, pois tem como haver departamentos diferentes entre as 2 tabelas
        List<Map<String, Set<Pessoa>>> departamentos = new ArrayList<>();
        departamentos = Collections.singletonList(repository.listAll().stream()
                .collect(groupingBy(Pessoa::getDepartamento, toSet())));
        List<Pessoa> listaDepartamento = new ArrayList<>();
        for (Map<String, Set<Pessoa>> departamento : departamentos) {
            Pessoa pessoa = new Pessoa();
            pessoa.setDepartamento(departamento.keySet().toString().replace("[", "").replace("]", ""));
            Long qtdPessoa = repository.listAll().stream().filter(x -> x.getDepartamento().equals(departamento.keySet().toString().replace("[", "").replace("]", ""))).mapToLong(Pessoa::getId).count();
            Long qtdTarefa = repositoryTarefa.listAll().stream().filter(x -> x.getDepartamento().equals(departamento.keySet().toString().replace("[", "").replace("]", ""))).mapToLong(Tarefa::getId).count();
            pessoa.setQtdPessoa(qtdPessoa);
            pessoa.setQtdTarefa(qtdTarefa);
            listaDepartamento.add(pessoa);
        }

        return listaDepartamento;
    }
}
