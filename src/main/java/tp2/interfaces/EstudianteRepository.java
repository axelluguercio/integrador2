package tp2.interfaces;

import org.apache.commons.csv.CSVParser;
import tp2.dto.DTOEstudiante;
import tp2.entidades.Carrera;
import tp2.entidades.Estudiante;

import java.sql.SQLException;
import java.util.List;

public interface EstudianteRepository {
    void insertEstudiante(Estudiante estudiante);

    void insert(CSVParser parser);

    List<Estudiante> obtenerTodosLosEstudiantesOrdenados();
    Estudiante obtenerEstudiantePorLibreta(String numeroLibreta);
    List<Estudiante> obtenerEstudiantesPorGenero(String genero);
    List<DTOEstudiante> obtenerEstudiantePorCarreraYCiudad(Carrera carrera, String ciudadResidencia);
    boolean existeEstudiante(Estudiante estudiante);

}
