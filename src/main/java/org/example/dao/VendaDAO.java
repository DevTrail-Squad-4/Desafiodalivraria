package org.example.dao;

import org.example.models.Venda;
import org.example.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class VendaDAO {

    public void salvar(Venda venda) {
        EntityTransaction transaction = null;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(venda);
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

    public List<Venda> listarTodos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("from Venda", Venda.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Venda buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    public void atualizar(Venda venda) {
        EntityTransaction transaction = null;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(venda);
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

    public void deletar(Venda venda) {
        EntityTransaction transaction = null;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            if (em.contains(venda)) {
                em.remove(venda);
            } else {
                Venda vendaManaged = em.find(Venda.class, venda.getId());
                if (vendaManaged != null) {
                    em.remove(vendaManaged);
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
}
