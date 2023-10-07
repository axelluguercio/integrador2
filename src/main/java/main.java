import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import tp2.repositories.CarreraRepositoryImpl;
import tp2.repositories.EstudianteCarreraRepositoryImpl;
import tp2.repositories.EstudianteRepositoryImpl;
import tp2.factory.FactoryEntityManager;

import javax.persistence.EntityManager;
import java.io.FileReader;

public class main {

    public static void main(String[] args) throws Exception {

        // mysql dao factory
        FactoryEntityManager factory_entity_manager = FactoryEntityManager.getEntityManagerFactory("MYSQL");
        EntityManager entity_manager = factory_entity_manager.connect("integrador2");

        // repositorios
        EstudianteRepositoryImpl estudiante_repository = factory_entity_manager.getEstudianteRepository(entity_manager);
        CarreraRepositoryImpl carrera_repository = factory_entity_manager.getCarreraRepository(entity_manager);
        EstudianteCarreraRepositoryImpl estudiante_carrera_repository = factory_entity_manager.getEstudianteCarreraRepository(entity_manager);

        // CSV parsers
        CSVParser CSV_carreras = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/CSV/carreras.csv"));
        CSVParser CSV_estudiantes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/CSV/estudiantes.csv"));
        CSVParser CSV_estudianteCarrera = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/CSV/estudianteCarrera.csv"));

        // carga de datos por csv (1 sola vez)
        //estudiante_repository.insert(CSV_estudiantes);
        //carrera_repository.insert(CSV_carreras);
        //estudiante_carrera_repository.insert(CSV_estudianteCarrera);

        // estudiante por genero y libreta
        //System.out.println(estudiante_repository.obtenerEstudiantePorLibreta("13413"));
        //List<Estudiante> porgenero = estudiante_repository.obtenerEstudiantesPorGenero("Male");

        //for (Estudiante est : porgenero) {
        //    System.out.println(est);
        //}

        // carreras con inscriptos, ordenado por cantidad de estudiantes
        //System.out.println(carrera_repository.obtenerCarreraConEstudiantesOrdenadas());

        // estudiantes de una carrera, filtrado por ciudad
        //System.out.println(estudiante_repository.obtenerEstudiantePorCarreraYCiudad(carrera_repository.obtenerCarreraPorNombre("Ingenieria Industrial"), "Paquera"));

        // reporte carrera
        //List<DTOReporteCarrera> reporte_carrera = estudiante_carrera_repository.getReporteCarrera();
        //System.out.println(reporte_carrera);

        // close
        factory_entity_manager.close();

    }
}
