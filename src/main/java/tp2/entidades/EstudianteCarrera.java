package tp2.entidades;
import tp2.dto.DTOEstudiante;
import tp2.entidades.Carrera;
import tp2.entidades.Estudiante;
import javax.persistence.*;
import java.util.List;

@Entity
public class EstudianteCarrera {


    @Id
    @Column(name = "id_estudiante_carrera")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "carrera")
    Carrera carrera;
    @Column(name = "anio")
    int anio;
    @Column(name = "duracion")
    int duracion;
    @Column(name = "estudiantes_inscriptos")
    List<Estudiante> estudiantesInscriptos;
    @Column(name = "estudiantes_egresados")
    List<Estudiante> estudiantesEgresados;



}
