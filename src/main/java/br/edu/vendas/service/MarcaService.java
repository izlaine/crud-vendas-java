package br.edu.vendas.service;

import br.edu.vendas.model.Marca;
import br.edu.vendas.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class MarcaService {
    @Inject MarcaRepository repo;
    public List<Marca> listar() { return repo.listar(); }
    public void salvar(Marca m) {
        if (m.getNome() == null || m.getNome().isBlank()) throw new IllegalArgumentException("Informe o nome da marca.");
        if (repo.existeNome(m.getNome(), m.getId())) throw new IllegalArgumentException("Já existe uma marca com esse nome.");
        repo.salvar(m);
    }
    public void excluir(Marca m) {
        if (repo.possuiProdutos(m)) throw new IllegalArgumentException("Não é possível excluir uma marca vinculada a produtos.");
        repo.excluir(m);
    }
}
