package tp2.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carrera")

public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "duracion")
    private int duracion;

    @ManyToMany
    private List<Estudiante> estudianteSet;

    public Carrera(){}
    public Carrera(String nombre, int duracion, List<Estudiante> estudianteSet) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.estudianteSet = estudianteSet;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}
