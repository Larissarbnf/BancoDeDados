package DAO;

import Model.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AlunoDAO {

    private EntityManagerFactory emf;

    public AlunoDAO() {
        this.emf = Persistence.createEntityManagerFactory("sistema-gerenciamento-notas");
    }

 
    public void salvar(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

   
    public Aluno buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Aluno.class, id);
        } finally {
            em.close();
        }
    }

    
    public List<Aluno> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Aluno", Aluno.class).getResultList();
        } finally {
            em.close();
        }
    }

 
    public void atualizar(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Aluno aluno = em.find(Aluno.class, id);
            if (aluno != null) {
                em.getTransaction().begin();
                em.remove(aluno);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    public void atribuirNota(int alunoId, double nota) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Aluno aluno = em.find(Aluno.class, alunoId);
            if (aluno != null) {
                aluno.setNota(nota);  
                em.merge(aluno);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
