package tp2.interfaces;

import org.apache.commons.csv.CSVParser;
import tp2.dto.DTOReporteCarrera;
import tp2.entidades.Carrera;

import java.sql.SQLException;
import java.util.List;

public interface CarreraRepository {
    void insertCarrera(Carrera carrera);

    void insert(CSVParser parser) throws SQLException;

    boolean existeCarrera(Carrera carrera);

    Carrera obtenerCarreraPorNombre(String nombre);

    List<Carrera> obtenerCarreraConEstudiantesOrdenadas();

}
