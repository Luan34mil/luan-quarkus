package tarefa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ITarefaRepository implements PanacheRepository<Tarefa> {
}