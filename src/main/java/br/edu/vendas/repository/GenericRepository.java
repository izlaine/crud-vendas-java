package br.edu.vendas.repository;

import br.edu.vendas.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

public abstract class GenericRepository<T> {
    private final Class<T> type;
    protected GenericRepository(Class<T> type) { this.type = type; }
    public void salvar(T obj) { executar(em -> { if (em.contains(obj)) em.merge(obj); else { try { var id=type.getMethod("getId").invoke(obj); if(id==null) em.persist(obj); else em.merge(obj); } catch(Exception e){ throw new RuntimeException(e); } } }); }
    public void excluir(T obj) { executar(em -> em.remove(em.contains(obj) ? obj : em.merge(obj))); }
    public T buscar(Long id) { try(EntityManager em=JPAUtil.getEntityManager()){ return em.find(type,id); } }
    protected List<T> consulta(String jpql) { try(EntityManager em=JPAUtil.getEntityManager()){ return em.createQuery(jpql,type).getResultList(); } }
    protected void executar(Consumer<EntityManager> action) { try(EntityManager em=JPAUtil.getEntityManager()){ EntityTransaction tx=em.getTransaction(); try{tx.begin(); action.accept(em); tx.commit();}catch(RuntimeException e){if(tx.isActive())tx.rollback();throw e;} } }
}
