package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestDatabase {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesafioLivraria");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            Person person = new Person();
            person.setName("John Doe");
            em.persist(person);
            tx.commit();

            System.out.println("Pessoa salva com ID: " + person.getId());

            em.close();
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
