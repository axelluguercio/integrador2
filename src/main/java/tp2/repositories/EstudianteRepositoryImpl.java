package tp2.repositories;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tp2.dto.DTOEstudiante;
import tp2.entidades.Carrera;
import tp2.entidades.Estudiante;
import tp2.interfaces.EstudianteRepository;

public class EstudianteRepositoryImpl implements EstudianteRepository {
    private EntityManager em;
    private EntityTransaction tx;

    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
        this.tx = this.em.getTransaction();
    }

    public void insertEstudiante(Estudiante estudiante) {
        if (!existeEstudiante(estudiante)) {
            if (!tx.isActive()) { // Verificar si no hay una transacción activa
                tx.begin();
            }
            try {
                this.em.persist(estudiante);
                this.tx.commit();
            } catch (Exception e) {
                // En caso de una excepción, revertir la transacción
                if (this.tx != null && this.tx.isActive()) {
                    this.tx.rollback();
                    e.printStackTrace();
                }
            }
        } else {
            throw new EntityExistsException("El estudiante ya existe");
        }
    }

    @Override
    public void insert(CSVParser parser) {
        for (CSVRecord row : parser) {
            String dni  = row.get("DNI");
            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            int edad = Integer.parseInt(row.get("edad"));
            String genero = row.get("genero");
            String ciudad = row.get("ciudad");
            String libreta = row.get("LU");

            Estudiante estudiante = new Estudiante(dni, nombre, apellido, edad, genero, ciudad, libreta);

            this.insertEstudiante(estudiante);
        }
    }

    public List<Estudiante> obtenerTodosLosEstudiantesOrdenados() {
        TypedQuery<Estudiante> query = this.em.createQuery(
         "SELECT e FROM Estudiante e " +
            "ORDER BY e.apellido, e.nombre",
            Estudiante.class);
        return query.getResultList();
    }

    public Estudiante obtenerEstudiantePorLibreta(String numeroLibreta) {
        TypedQuery<Estudiante> query = this.em.createQuery(
         "SELECT e FROM Estudiante e " +
            "WHERE e.num_libreta = :libreta",
            Estudiante.class);
        query.setParameter("libreta", numeroLibreta);
        return query.getSingleResult();
    }

    public List<Estudiante> obtenerEstudiantesPorGenero(String genero) {
        TypedQuery<Estudiante> query = this.em.createQuery(
         "SELECT e FROM Estudiante e  " +
            "WHERE e.genero = :genero",
            Estudiante.class);
        query.setParameter("genero", genero);
        return query.getResultList();
    }

    @Override
    public List<DTOEstudiante> obtenerEstudiantePorCarreraYCiudad(Carrera carrera, String ciudadResidencia) {
        TypedQuery<DTOEstudiante> query = this.em.createQuery(
         "SELECT new tp2.dto.DTOEstudiante (e.dni, e.nombre, e.apellido, e.edad, e.genero, e.ciudad, e.num_libreta, CASE WHEN ec.anio_graduacion != 0 THEN true ELSE false END) " +
            "FROM EstudianteCarrera ec " +
            "JOIN ec.estudiante e " +
            "JOIN ec.carrera c " +
            "WHERE c = :carrera AND e.ciudad = :ciudad",
            DTOEstudiante.class);
        query.setParameter("carrera", carrera);
        query.setParameter("ciudad", ciudadResidencia);
        return query.getResultList();
    }

    public boolean existeEstudiante(Estudiante estudiante) {
        TypedQuery<Estudiante> query = this.em.createQuery(
         "SELECT e FROM Estudiante e " +
            "WHERE e.num_libreta = :libreta ",
            Estudiante.class);
        query.setParameter("libreta", estudiante.getNum_libreta());
        return !query.getResultList().isEmpty();
    }
}
