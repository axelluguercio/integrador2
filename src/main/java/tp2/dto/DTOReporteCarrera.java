package tp2.dto;

public class DTOReporteCarrera {

    private String nombre;
    private int anio;
    private long inscriptos;
    private long egresados;

    public DTOReporteCarrera(String nombre, int anio, long cant_inscriptos, long cant_egresados) {
        this.nombre = nombre;
        this.anio = anio;
        this.inscriptos = cant_inscriptos;
        this.egresados = cant_egresados;
    }

    public String getNombre() {
        return nombre;
    }

    public long getAnio() {
        return anio;
    }

    public long getInscriptos() {
        return inscriptos;
    }

    public long getEgresados() {
        return egresados;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setInscriptos(int inscriptos) {
        this.inscriptos = inscriptos;
    }

    public void setEgresados(int egresados) {
        this.egresados = egresados;
    }

    @Override
    public String toString() {
        return "DTOReporteCarrera{" +
                "nombre='" + nombre + '\'' +
                ", anio=" + anio +
                ", inscriptos=" + inscriptos +
                ", egresados=" + egresados +
                '}';
    }
}
