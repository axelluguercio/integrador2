package tp2.dao;

import tp2.entidades.*

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAOEstudiante {

    private EntityManager em;

    private EntityTransaction tx;

    public DAOEstudiante(EntityManager em) {
        this.em = em;
        this.tx = this.em.getTransaction();
    }

    public void insertEstudiante(Estudiante estudiante) {
        this.tx.begin();
        em.persist(estudiante);
        tx.commit();
    }

    public void matricularEstudianteEnCarrera(Estudiante estudiante, Carrera carrera) {
        this.tx.begin();
        estudiante.getCarreras().add(carrera);
        this.em.merge(estudiante);
        tx.commit();
    }

    public List<Estudiante> obtenerTodosLosEstudiantesOrdenados() {
        TypedQuery<Estudiante> query = this.em.createQuery("SELECT e FROM estudiante e ORDER BY e.apellido, e.nombre", Estudiante.class);
        return query.getResultList();
    }

    public Estudiante obtenerEstudiantePorLibreta(String numeroLibreta) {

        TypedQuery<Estudiante> query = this.em.createQuery("SELECT e FROM estudiante e WHERE e.num_libreta = :libreta", Estudiante.class);
        query.setParameter("libreta", numeroLibreta);
        return query.getSingleResult();
    }

    public List<Estudiante> obtenerEstudiantesPorGenero(String genero) {
        TypedQuery<Estudiante> query = this.em.createQuery("SELECT e FROM estudiante e WHERE e.genero = :genero", Estudiante.class);
        query.setParameter("genero", genero);
        return query.getResultList();
    }

    public List<Carrera> obtenerCarrerasConEstudiantesOrdenadas() {
        TypedQuery<Carrera> query = this.em.createQuery("SELECT c FROM carrera c WHERE SIZE(c.estudiantes) > 0 ORDER BY SIZE(c.estudiantes) DESC", Carrera.class);
        return query.getResultList();
    }

    public List<Estudiante> obtenerEstudiantesPorCarreraYCiudad(Carrera carrera, String ciudadResidencia) {
        TypedQuery<Estudiante> query = this.em.createQuery("SELECT e FROM estudiante e JOIN e.carreras c WHERE c = :carrera AND e.ciudadResidencia = :ciudad", Estudiante.class);
        query.setParameter("carrera", carrera);
        query.setParameter("ciudad", ciudadResidencia);
        return query.getResultList();
    }







}
