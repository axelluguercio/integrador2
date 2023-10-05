package tp2.dao;

import tp2.dto.DTOReporteCarrera;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOCarrera {
    private EntityManager em;

    public DAOCarrera(EntityManager em){
        this.em = em;
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

}