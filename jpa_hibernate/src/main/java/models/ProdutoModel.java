package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Produto;

public class ProdutoModel {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");

    public void create(Produto p) {

        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Produto p) {
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.remove(em.contains(p) ? p : em.merge(p));
            em.getTransaction().commit();
            System.out.println("Produto deletado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto findById(Produto produto) {
        EntityManager em = emf.createEntityManager();
        Produto p = null;
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            p = em.find(Produto.class, produto.getId());
            em.getTransaction().commit();
            System.out.println("Produto encontrado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao encontrar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return p;
    }

    public List<Produto> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Produto> produtos = new ArrayList<Produto>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            produtos = em.createQuery("FROM Produto", Produto.class).getResultList();
            em.getTransaction().commit();
            System.out.println("Produtos lidos com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao ler os produtos !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }

        return produtos;
    }
}
