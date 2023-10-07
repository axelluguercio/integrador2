package tp2.dto;
import tp2.entidades.Carrera;
import java.util.List;

public class DTOEstudiante {
    String id;
    String nombre;
    String apellido;
    int edad;
    String genero;
    String ciudad;
    String libreta;
    boolean graduado;

    public DTOEstudiante(String dni,
                         String nombre,
                         String apellido,
                         int edad,
                         String genero,
                         String ciudad,
                         String num_libreta,
                         boolean graduado){
        this.id = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.libreta = num_libreta;
        this.graduado = graduado;
    }

    public String getId(){
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getFecha() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getLibreta() {
        return libreta;
    }

    public boolean isGraduado() {
        return graduado;
    }

    @Override
    public String toString() {
        return "DTOEstudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad='" + edad + '\'' +
                ", genero='" + genero + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", libreta='" + libreta + '\'' +
                ", graduado=" + graduado +
                '}';
    }
}
