package tarefa;

import pessoa.IPessoaRepository;
import pessoa.Pessoa;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/tarefas")
public class TarefaControlador {

    @Inject
    ITarefaRepository repository;

    @Inject
    IPessoaRepository repositoryPessoa;

    @POST
    @Transactional
    public void salvar(Tarefa tarefa) {
        repository.persist(tarefa);
    }

    @PUT
    @Transactional
    @Path("/alocar/id/{id}")
    public void alocar(@PathParam("id") Long id) {
        //normalmente chamaria a função do controlador por um extends na class, mas para fins didátivos chamei direto aqui
        Tarefa tarefa = repository.findById(id);
        Pessoa pessoa = repositoryPessoa.listAll().stream().filter(x-> x.getDepartamento().equals(tarefa.getDepartamento())).findFirst().get();

        tarefa.setPessoa(pessoa);
        repository.persist(tarefa);

        Pessoa pessoaSalvar = repositoryPessoa.findById(pessoa.id);
        List<Tarefa> tarefaSalva = new ArrayList<>();
        tarefaSalva.add(tarefa);

        pessoaSalvar.setTarefas(tarefaSalva);

        repositoryPessoa.persist(pessoaSalvar);
    }

    @PUT
    @Transactional
    @Path("/finalizar/id/{id}")
    public void finalizar(@PathParam("id") Long id) {

        Tarefa tarefa = repository.findById(id);
        tarefa.setId(id);
        tarefa.setStatus("Finalizado");

        repository.persist(tarefa);
    }

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tarefa> findAll() {
        return repository.listAll();
    }

    @GET
    @Transactional
    @Path("/pendentes")
    public List<Tarefa> pendentes() {
        List<Tarefa> tarefas = repository.listAll().stream().filter(x -> x.getPessoa() == null).collect(Collectors.toList());

        //faria uma consulta nativeQuery com top 3
        Comparator<Tarefa> compareByPrazo = (Tarefa o1, Tarefa o2) -> o1.getPrazo().compareTo( o2.getPrazo() );

        Collections.sort(tarefas, compareByPrazo.reversed());

        List<Tarefa> tarefasEscolhidas  = new ArrayList<>();
        if (tarefas.size() > 2) {
            tarefasEscolhidas.add(tarefas.get(0));
            tarefasEscolhidas.add(tarefas.get(1));
            tarefasEscolhidas.add(tarefas.get(2));
        } else if (tarefas.size() > 1) {
            tarefasEscolhidas.add(tarefas.get(0));
            tarefasEscolhidas.add(tarefas.get(1));
        } else if (tarefas.size() == 1) {
            tarefasEscolhidas.add(tarefas.get(0));
        }

        return tarefasEscolhidas;
    }
}
