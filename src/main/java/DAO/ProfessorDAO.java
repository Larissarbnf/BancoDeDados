package DAO;

import Model.Professor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProfessorDAO {

    private EntityManagerFactory emf;

    public ProfessorDAO() {
        this.emf = Persistence.createEntityManagerFactory("sistema-gerenciamento-notas");
    }

   
    public void salvar(Professor professor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(professor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Professor buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Professor.class, id);
        } finally {
            em.close();
        }
    }

    public List<Professor> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Professor", Professor.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Professor professor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(professor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Professor professor = em.find(Professor.class, id);
            if (professor != null) {
                em.getTransaction().begin();
                em.remove(professor);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
