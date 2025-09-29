package models;

import entities.Curso;
import javax.persistence.*;
import java.util.List;

public class CursoModel {
    
    public void create(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public Curso findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Curso.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso por ID: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Curso> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            Query query = em.createQuery("SELECT c FROM Curso c", Curso.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os cursos: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
    public void update(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação para atualização");
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar curso: " + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public void delete(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            System.out.println("Iniciando a transação para exclusão");
            em.getTransaction().begin();
            Curso cursoToDelete = em.find(Curso.class, curso.getId());
            if (cursoToDelete != null) {
                em.remove(cursoToDelete);
                em.getTransaction().commit();
                System.out.println("Curso deletado com sucesso !!!");
            } else {
                System.out.println("Curso não encontrado para exclusão");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao deletar curso: " + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
}