package tp2.dto;

public class DTOInscriptosCarrera {

    private String id;
    private String nombre;
    private int duracion;
    private long inscriptos;

    public DTOInscriptosCarrera(String id, String nombre, int duracion, long inscriptos) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.inscriptos = inscriptos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public long getInscriptos() {
        return inscriptos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setInscriptos(long inscriptos) {
        this.inscriptos = inscriptos;
    }

    @Override
    public String toString() {
        return "DTOInscriptosCarrera{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", inscriptos=" + inscriptos +
                '}';
    }
}
