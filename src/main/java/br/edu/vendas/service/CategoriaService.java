package br.edu.vendas.service;

import br.edu.vendas.model.Categoria;
import br.edu.vendas.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class CategoriaService {
    @Inject CategoriaRepository repo;

    public List<Categoria> listar() { return repo.listar(); }

    public void salvar(Categoria c) {
        if (c.getNome() == null || c.getNome().isBlank()) {
            throw new IllegalArgumentException("Informe o nome da categoria.");
        }
        if (c.getCategoriaPai() != null && c.getId() != null) {
            if (c.getCategoriaPai().getId().equals(c.getId())) {
                throw new IllegalArgumentException("Uma categoria não pode ser pai dela mesma.");
            }
            Set<Long> visitados = new HashSet<>();
            Categoria atual = c.getCategoriaPai();
            while (atual != null) {
                if (atual.getId() != null && !visitados.add(atual.getId())) {
                    throw new IllegalArgumentException("A hierarquia informada contém um ciclo.");
                }
                if (atual.getId() != null && atual.getId().equals(c.getId())) {
                    throw new IllegalArgumentException("Não é possível definir uma subcategoria como pai de sua própria categoria.");
                }
                atual = atual.getCategoriaPai();
            }
        }
        repo.salvar(c);
    }

    public void excluir(Categoria c) {
        if (repo.possuiFilhos(c)) {
            throw new IllegalArgumentException("Não é possível excluir uma categoria que possui subcategorias.");
        }
        if (repo.possuiProdutos(c)) {
            throw new IllegalArgumentException("Não é possível excluir uma categoria vinculada a produtos.");
        }
        repo.excluir(c);
    }
}
