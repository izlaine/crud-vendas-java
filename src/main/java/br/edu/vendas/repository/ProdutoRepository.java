package br.edu.vendas.repository;
import br.edu.vendas.model.Produto;
import java.util.List;
public class ProdutoRepository extends GenericRepository<Produto>{
 public ProdutoRepository(){super(Produto.class);}
 public List<Produto> listar(){return consulta("select p from Produto p join fetch p.marca join fetch p.categoria order by p.nome");}
}
