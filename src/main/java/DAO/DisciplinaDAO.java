package DAO;

import Model.Disciplina;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DisciplinaDAO {

    private EntityManagerFactory emf;

    public DisciplinaDAO() {
        this.emf = Persistence.createEntityManagerFactory("sistema-gerenciamento-notas");
    }

    public void salvar(Disciplina disciplina) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(disciplina);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Disciplina buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Disciplina.class, id);
        } finally {
            em.close();
        }
    }

    public List<Disciplina> listarTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Disciplina", Disciplina.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Disciplina disciplina) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(disciplina);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Disciplina disciplina = em.find(Disciplina.class, id);
            if (disciplina != null) {
                em.getTransaction().begin();
                em.remove(disciplina);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
