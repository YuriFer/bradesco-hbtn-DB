package models;

import entities.Aluno;
import javax.persistence.*;
import java.util.List;

public class AlunoModel {
    
    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public Aluno findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Aluno.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar aluno por ID: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Aluno> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            Query query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os alunos: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
    public void update(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação para atualização");
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public void delete(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação para exclusão");
            em.getTransaction().begin();
            Aluno alunoToDelete = em.find(Aluno.class, aluno.getId());
            if (alunoToDelete != null) {
                em.remove(alunoToDelete);
                em.getTransaction().commit();
                System.out.println("Aluno deletado com sucesso !!!");
            } else {
                System.out.println("Aluno não encontrado para exclusão");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao deletar aluno: " + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
}