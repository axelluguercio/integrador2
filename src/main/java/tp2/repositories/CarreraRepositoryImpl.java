package tp2.repositories;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tp2.dto.DTOReporteCarrera;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tp2.entidades.Carrera;
import tp2.entidades.Estudiante;
import tp2.entidades.EstudianteCarrera;
import tp2.interfaces.CarreraRepository;

public class CarreraRepositoryImpl implements CarreraRepository {
    private EntityManager em;
    private EntityTransaction tx;
    public CarreraRepositoryImpl(EntityManager em){
        this.em = em;
        this.tx = this.em.getTransaction();
    }

    public void insertCarrera(Carrera carrera){
        if (!existeCarrera(carrera)){
            if (!this.tx.isActive()) { // Verificar si no hay una transacción activa
                this.tx.begin();
            }
            try {
                this.em.persist(carrera);
                this.tx.commit();
            } catch (Exception e) {
                // En caso de una excepción, revertir la transacción
                if (this.tx != null && this.tx.isActive()) {
                    this.tx.rollback();
                    e.printStackTrace();
                }
            }
        }else{
            throw new EntityExistsException("La carrera ya existe");
        }
    }

    @Override
    public void insert(CSVParser parser) throws SQLException {
        for (CSVRecord row : parser) {
            String id = row.get("id_carrera");
            String nombre = row.get("carrera");
            int duracion = Integer.parseInt(row.get("duracion"));

            Carrera carrera = new Carrera(id, nombre, duracion);

            this.insertCarrera(carrera);
        }
    }

    public boolean existeCarrera(Carrera carrera){
        TypedQuery<Carrera> query = this.em.createQuery(
     "SELECT c FROM Carrera " +
        "c WHERE c.nombre = :nombre",
        Carrera.class);
        query.setParameter("nombre", carrera.getNombre());
        List<Carrera> carreras = query.getResultList();
        return !carreras.isEmpty();
    }

    public Carrera obtenerCarreraPorNombre(String nombre) {
        TypedQuery<Carrera> query = this.em.createQuery(
     "SELECT c FROM Carrera " +
        "c WHERE c.nombre = :nombre",
        Carrera.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }

    public List<Carrera> obtenerCarreraConEstudiantesOrdenadas() {
        TypedQuery<Carrera> query = this.em.createQuery(
     "SELECT c FROM Carrera " +
        "c WHERE SIZE(c.estudiantes) > 0 " +
        "ORDER BY SIZE(c.estudiantes) DESC",
        Carrera.class);
        return query.getResultList();
    }
}