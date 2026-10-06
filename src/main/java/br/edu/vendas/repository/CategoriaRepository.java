package br.edu.vendas.repository;

import br.edu.vendas.model.Categoria;
import java.util.List;

public class CategoriaRepository extends GenericRepository<Categoria> {
    public CategoriaRepository() { super(Categoria.class); }

    public List<Categoria> listar() {
        return consulta("select c from Categoria c left join fetch c.categoriaPai order by c.nome");
    }

    public List<Categoria> listarRaizes() {
        return consulta("select c from Categoria c where c.categoriaPai is null order by c.nome");
    }

    public boolean possuiFilhos(Categoria c) {
        try (var em = br.edu.vendas.util.JPAUtil.getEntityManager()) {
            return em.createQuery("select count(x) from Categoria x where x.categoriaPai.id=:id", Long.class)
                    .setParameter("id", c.getId()).getSingleResult() > 0;
        }
    }

    public boolean possuiProdutos(Categoria c) {
        try (var em = br.edu.vendas.util.JPAUtil.getEntityManager()) {
            return em.createQuery("select count(p) from Produto p where p.categoria.id=:id", Long.class)
                    .setParameter("id", c.getId()).getSingleResult() > 0;
        }
    }
}
