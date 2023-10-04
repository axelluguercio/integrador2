package tp2.dao;

import tp2.entidades.*;
import javax.persistence.EntityManager;

public class DAOCarrera {
    private EntityManager em;

    public DAOCarrera(){}
    public void insertEstudiante(Estudiante estudiante) {
        EntityManager em = // Obtener el EntityManager
                EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(estudiante);
        tx.commit();
    }

}
