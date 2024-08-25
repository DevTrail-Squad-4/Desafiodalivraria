package org.example.dao;

import org.example.models.Livro;
import org.example.models.Impresso;
import org.example.models.Eletronico;
import org.example.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class LivroDAO {

    public void salvar(Livro livro) {
        EntityTransaction transaction = null;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(livro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Livro> listarTodos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("from Livro", Livro.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Livro buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Livro.class, id);
        } finally {
            em.close();
        }
    }

    public void atualizar(Livro livro) {
        EntityTransaction transaction = null;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(livro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deletar(Livro livro) {
        EntityTransaction transaction = null;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            if (em.contains(livro)) {
                em.remove(livro);
            } else {
                Livro livroManaged = em.find(Livro.class, livro.getId());
                if (livroManaged != null) {
                    em.remove(livroManaged);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Impresso> listarImpressos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Impresso> query = em.createQuery("select i from Impresso i", Impresso.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Eletronico> listarEletronicos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Eletronico> query = em.createQuery("select e from Eletronico e", Eletronico.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
