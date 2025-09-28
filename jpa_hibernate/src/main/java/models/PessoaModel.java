package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Pessoa;

public class PessoaModel {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");

    public void create(Pessoa p) {
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa cadastrada com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao cadastrar a pessoa !!!" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Finalizando a transação");
    }

    public void update(Pessoa p) {
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Finalizando a transação");
    }

    public void delete(Pessoa p) {
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.remove(em.contains(p) ? p : em.merge(p));
            em.getTransaction().commit();
            System.out.println("Pessoa deletada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa findById(Pessoa p) {
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            p = em.find(Pessoa.class, p.getId());
            em.getTransaction().commit();
            System.out.println("Pessoa encontrada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao encontrar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return p;
    }

    public List<Pessoa> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            pessoas = em.createQuery("FROM Pessoa", Pessoa.class).getResultList();
            em.getTransaction().commit();
            System.out.println("Pessoas encontradas com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao encontrar as pessoas !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return pessoas;
    }
}
