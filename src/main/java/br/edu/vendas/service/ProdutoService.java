package br.edu.vendas.service;
import br.edu.vendas.model.Produto; import br.edu.vendas.repository.ProdutoRepository; import jakarta.enterprise.context.ApplicationScoped; import jakarta.inject.Inject; import java.util.List;
@ApplicationScoped public class ProdutoService { @Inject ProdutoRepository repo; public List<Produto> listar(){return repo.listar();} public void salvar(Produto p){repo.salvar(p);} public void excluir(Produto p){repo.excluir(p);} }
