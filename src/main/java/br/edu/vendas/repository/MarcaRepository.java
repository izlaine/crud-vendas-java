package br.edu.vendas.repository;

import br.edu.vendas.model.Marca;
import br.edu.vendas.util.JPAUtil;
import java.util.List;

public class MarcaRepository extends GenericRepository<Marca> {
    public MarcaRepository() { super(Marca.class); }
    public List<Marca> listar() { return consulta("select m from Marca m order by m.nome"); }
    public boolean existeNome(String nome, Long id) {
        try (var em = JPAUtil.getEntityManager()) {
            var q = em.createQuery("select count(m) from Marca m where lower(trim(m.nome))=lower(trim(:nome)) and (:id is null or m.id<>:id)", Long.class);
            q.setParameter("nome", nome);
            q.setParameter("id", id);
            return q.getSingleResult() > 0;
        }
    }
    public boolean possuiProdutos(Marca m) {
        try (var em = JPAUtil.getEntityManager()) {
            return em.createQuery("select count(p) from Produto p where p.marca.id=:id", Long.class)
                    .setParameter("id", m.getId()).getSingleResult() > 0;
        }
    }
}
