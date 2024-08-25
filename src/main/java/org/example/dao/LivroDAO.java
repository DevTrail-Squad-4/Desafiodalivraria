package org.example.dao;

import org.example.models.Livro;
import org.example.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

    public List<Livro> listarEletronicos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livro l WHERE l.tipo = :tipo", Livro.class)
                     .setParameter("tipo", "eletronico")
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Livro> listarImpressos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livro l WHERE l.tipo = :tipo", Livro.class)
                     .setParameter("tipo", "impresso")
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
