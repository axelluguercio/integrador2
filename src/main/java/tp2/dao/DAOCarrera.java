package tp2.dao;

import tp2.dto.DTOReporteCarrera;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import tp2.entidades.Carrera;

public class DAOCarrera {
    private EntityManager em;
    private EntityTransaction tx;
    public DAOCarrera(EntityManager em){
        this.em = em;
        this.tx = this.em.getTransaction();
    }

    public List<DTOReporteCarrera> getReporteCarrera() {
        // Consulta JPQL para obtener el informe
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT c.nombre, YEAR(e.fechaInscripcion), COUNT(e), SUM(CASE WHEN e.graduado = true THEN 1 ELSE 0 END) " +
                        "FROM Carrera c LEFT JOIN c.estudiantes e " +
                        "GROUP BY c.nombre, YEAR(e.fechaInscripcion) " +
                        "ORDER BY c.nombre ASC, YEAR(e.fechaInscripcion) ASC",
                Object[].class
        );

        List<Object[]> resultados = query.getResultList();

        // Procesar los resultados y crear objetos ReporteCarreraDTO
        List<DTOReporteCarrera> reporte = new ArrayList<>();
        for (Object[] resultado : resultados) {
            String nombre = (String) resultado[0];
            int anio = (int) resultado[1];
            long inscriptos = (Long) resultado[2];
            long egresados = (Long) resultado[3];
            //dto.setNombreCarrera((String) resultado[0]);
            //dto.setAño((int) resultado[1]);
            //dto.setInscritos(((Long) resultado[2]).longValue());
            //dto.setEgresados(((Long) resultado[3]).longValue());
            DTOReporteCarrera dto = new DTOReporteCarrera(nombre, anio, inscriptos, egresados);
            reporte.add(dto);
        }

        return reporte;
    }

    public void insertCarrera(Carrera carrera){

        if(!existeCarrera(carrera)){
            if (!tx.isActive()) { // Verificar si no hay una transacción activa
                tx.begin();
            }

            try {
                em.persist(carrera);
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


    public boolean existeCarrera(Carrera carrera){
        TypedQuery<Carrera> query = this.em.createQuery("SELECT c FROM Carrera c WHERE c.nombre = :nombre", Carrera.class);
        query.setParameter("nombre", carrera.getNombre());
        List<Carrera> carreras = query.getResultList();
        return !carreras.isEmpty();
    }

    public Carrera obtenerCarreraPorNombre(String ingenieriaEnSistemasDeInformacion) {
        TypedQuery<Carrera> query = this.em.createQuery("SELECT c FROM Carrera c WHERE c.nombre = :nombre", Carrera.class);
        query.setParameter("nombre", ingenieriaEnSistemasDeInformacion);
        return query.getSingleResult();
    }
}