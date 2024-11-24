package DAO;

import Model.Turma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TurmaDAO {

    private EntityManagerFactory emf;

    public TurmaDAO() {
        this.emf = Persistence.createEntityManagerFactory("sistema-gerenciamento-notas");
    }

    
    public void salvar(Turma turma) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(turma);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Turma buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Turma.class, id);
        } finally {
            em.close();
        }
    }

    public List<Turma> listarTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Turma", Turma.class).getResultList();
        } finally {
            em.close();
        }
    }

   
    public void atualizar(Turma turma) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(turma);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public void deletar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Turma turma = em.find(Turma.class, id);
            if (turma != null) {
                em.getTransaction().begin();
                em.remove(turma);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
