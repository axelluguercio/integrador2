package tp2.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import tp2.entidades.Carrera;
import tp2.entidades.Estudiante;

public class DAOEstudiante {

    private EntityManager em;

    private EntityTransaction tx;

    public DAOEstudiante(EntityManager em) {
        this.em = em;
        this.tx = this.em.getTransaction();
    }

    public void insertEstudiante(Estudiante estudiante) {
        if (!tx.isActive()) { // Verificar si no hay una transacción activa
            tx.begin();
        }
        try {
            em.persist(estudiante);
            this.tx.commit();
        } catch (Exception e) {
            // En caso de una excepción, revertir la transacción
            if (this.tx != null && this.tx.isActive()) {
                this.tx.rollback();
                e.printStackTrace();
            }
        }
    }

    public void matricularEstudianteEnCarrera(Estudiante estudiante, Carrera carrera) {
        this.tx.begin();
        estudiante.getCarreras().add(carrera);
        em.merge(estudiante);
        tx.commit();
    }

    public List<Estudiante> obtenerTodosLosEstudiantesOrdenados() {
        TypedQuery<Estudiante> query = this.em.createQuery("SELECT e FROM Estudiante e ORDER BY e.apellido, e.nombre", Estudiante.class);
        return query.getResultList();
    }

    public Estudiante obtenerEstudiantePorLibreta(String numeroLibreta) {
        TypedQuery<Estudiante> query = this.em.createQuery("SELECT e FROM Estudiante e WHERE e.num_libreta = :libreta", Estudiante.class);
        query.setParameter("libreta", numeroLibreta);
        return query.getSingleResult();
    }

    public List<Estudiante> obtenerEstudiantesPorGenero(String genero) {
        TypedQuery<Estudiante> query = this.em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class);
        query.setParameter("genero", genero);
        return query.getResultList();
    }

    public List<Carrera> obtenerCarrerasConEstudiantesOrdenadas() {
        TypedQuery<Carrera> query = this.em.createQuery("SELECT c FROM Carrera c WHERE SIZE(c.estudiantes) > 0 ORDER BY SIZE(c.estudiantes) DESC", Carrera.class);
        return query.getResultList();
    }

    public List<Estudiante> obtenerEstudiantesPorCarreraYCiudad(Carrera carrera, String ciudadResidencia) {
        TypedQuery<Estudiante> query = this.em.createQuery("SELECT e FROM Estudiante e JOIN e.carreras c WHERE c = :carrera AND e.ciudadResidencia = :ciudad", Estudiante.class);
        query.setParameter("carrera", carrera);
        query.setParameter("ciudad", ciudadResidencia);
        return query.getResultList();
    }

}
