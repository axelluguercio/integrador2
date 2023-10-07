package tp2.interfaces;

import org.apache.commons.csv.CSVParser;
import tp2.dto.DTOReporteCarrera;
import tp2.entidades.EstudianteCarrera;

import java.sql.SQLException;
import java.util.List;

public interface EstudianteCarreraRepository {

    List<DTOReporteCarrera> getReporteCarrera();
    void matricularEstudianteEnCarrera(EstudianteCarrera matriculacion);
    void insert(CSVParser parser);

}
