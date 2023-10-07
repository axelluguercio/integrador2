package tp2.entidades;

import javax.persistence.*;

@Entity
@Table(name = "estudiante_carrera")

public class EstudianteCarrera {
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column(name = "inscripcion")
    private int anio;

    @Column(name = "graduacion")
    private int anio_graduacion;

    @Column(name = "antiguedad")
    private int antiguedad;

    public EstudianteCarrera(String id, Carrera carrera, Estudiante estudiante, int anio, int graduacion, int antiguedad) {
        this.id = id;
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.anio = anio;
        this.anio_graduacion = graduacion;
        this.antiguedad = antiguedad;
    }

}
