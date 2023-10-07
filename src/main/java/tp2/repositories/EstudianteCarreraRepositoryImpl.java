package tp2.repositories;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tp2.dto.DTOReporteCarrera;
import tp2.entidades.Carrera;
import tp2.entidades.Estudiante;
import tp2.entidades.EstudianteCarrera;
import tp2.interfaces.EstudianteCarreraRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteCarreraRepositoryImpl implements EstudianteCarreraRepository {
    private EntityManager em;
    private EntityTransaction tx;
    public EstudianteCarreraRepositoryImpl(EntityManager em){
        this.em = em;
        this.tx = this.em.getTransaction();
    }

    @Override
    public List<DTOReporteCarrera> getReporteCarrera() {
        // Consulta JPQL para obtener el informe
        TypedQuery<DTOReporteCarrera> query = this.em.createQuery(
                "SELECT new DTOReporteCarrera(c.nombre, ec.anio, COUNT(e), SUM(CASE WHEN ec.anio_graduacion != 0 THEN 1 ELSE 0 END))" +
                        "FROM EstudianteCarrera ec " +
                        "JOIN ec.carrera.id c " +
                        "JOIN ec.estudiante.dni e " +
                        "GROUP BY c.nombre, ec.anio " +
                        "ORDER BY c.nombre ASC, ec.anio ASC",
                DTOReporteCarrera.class
        );
        List<DTOReporteCarrera> resultados = query.getResultList();
        // Procesar los resultados y crear objetos ReporteCarreraDTO
        //List<DTOReporteCarrera> reporte = new ArrayList<>();
        /*/
        for (Object[] resultado : resultados) {
            String nombre = (String) resultado[0];
            int anio = (int) resultado[1];
            long inscriptos = (Long) resultado[2];
            long egresados = (Long) resultado[3];
            DTOReporteCarrera dto = new DTOReporteCarrera(nombre, anio, inscriptos, egresados);
            reporte.add(dto);
        }
        /*/
        return resultados;
    }

    @Override
    public void matricularEstudianteEnCarrera(EstudianteCarrera matriculacion) {
        if (!this.tx.isActive()) { // Verificar si no hay una transacción activa
            this.tx.begin();
        }
        try {
            this.em.persist(matriculacion);
            this.tx.commit();
        }
        catch (Exception e) {
            // En caso de una excepción, revertir la transacción
            if (this.tx != null && this.tx.isActive()) {
                this.tx.rollback();
                e.printStackTrace();
            }
        }
    }

    public Carrera getCarreraByID(String id) {
        TypedQuery<Carrera> query = this.em.createQuery(
                "SELECT e FROM Carrera e " +
                        "WHERE e.id = :id",
                Carrera.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Estudiante getEstudianteByID(String id) {
        TypedQuery<Estudiante> query = this.em.createQuery(
                "SELECT e FROM Estudiante e " +
                        "WHERE e.dni = :id",
                Estudiante.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void insert(CSVParser parser) {
        for (CSVRecord row : parser) {
            String id  = row.get("id");
            String id_estudiante = row.get("id_estudiante");
            String id_carrera = row.get("id_carrera");
            int inscripcion = Integer.parseInt(row.get("inscripcion"));
            int graduacion = Integer.parseInt(row.get("graduacion"));
            int antiguedad = Integer.parseInt(row.get("antiguedad"));

            // estudiante
            Estudiante estudiante = this.getEstudianteByID(id_estudiante);

            // carrera
            Carrera carrera = this.getCarreraByID(id_carrera);

            EstudianteCarrera matriculacion = new EstudianteCarrera(id, carrera, estudiante, inscripcion, graduacion, antiguedad);

            this.matricularEstudianteEnCarrera(matriculacion);
        }
    }
}
