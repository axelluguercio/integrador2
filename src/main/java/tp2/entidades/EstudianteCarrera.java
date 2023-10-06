package tp2.entidades;

import javax.persistence.*;

@Entity
@Table(name = "estudiante-carrera")

public class EstudianteCarrera {


    @Id
    @Column(name = "id_estudiante_carrera")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carrera")
    private Carrera carrera;

    @Column(name = "anio")
    private int anio;

    @Column(name = "duracion")
    private int duracion;

    private Estudiante estudiante;

    public EstudianteCarrera(Carrera carrera, int anio, int duracion, Estudiante estudiante) {

        this.carrera = carrera;
        this.anio = anio;
        this.duracion = duracion;
        this.estudiante = estudiante;
    }

}
