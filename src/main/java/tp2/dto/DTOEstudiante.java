package tp2.dto;
import tp2.entidades.Carrera;
import java.util.List;

public class DTOEstudiante {
    Long id;
    String nombre;
    String apellido;
    String fecha;
    String genero;
    String documento;
    String ciudad;
    String libreta;
    boolean graduado;
    List<Carrera> carreras;


    public DTOEstudiante(){};

    public Long getId(){
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFecha() {
        return fecha;
    }

    public String getGenero() {
        return genero;
    }

    public String getDocumento() {
        return documento;
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

    public List<Carrera> getCarreras() {
        return carreras;
    }
}
