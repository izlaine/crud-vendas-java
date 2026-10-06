package br.edu.vendas.repository;
import jakarta.enterprise.context.Dependent; import jakarta.enterprise.inject.Produces;
public class RepositoryProducer {
 @Produces @Dependent public MarcaRepository marcaRepository(){return new MarcaRepository();}
 @Produces @Dependent public CategoriaRepository categoriaRepository(){return new CategoriaRepository();}
 @Produces @Dependent public ProdutoRepository produtoRepository(){return new ProdutoRepository();}
}
