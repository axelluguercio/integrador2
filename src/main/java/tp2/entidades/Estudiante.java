package tp2.entidades;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "estudiante")

public class Estudiante {
    @Id
    @Column(name = "id_estudiante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apelllido")
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "numero_documento")
    private String documento;

    @Column(name = "ciudad")
    private String ciudad;


    @Column(name = "num_libreta")
    private String num_libreta;

    @Column(name = "graduado")
    private boolean graduado;

    @ManyToMany(mappedBy = "estudianteSet")
    private Set<Carrera> carreraSet;

    public Estudiante(){}

    public String getNombre() {
        return nombre;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
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

    public String getNum_libreta() {
        return num_libreta;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Set<Carrera> getCarreras() {
        return carreraSet;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setNum_libreta(String num_libreta) {
        this.num_libreta = num_libreta;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", genero='" + genero + '\'' +
                ", documento='" + documento + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", num_libreta='" + num_libreta + '\'' +
                ", graduado=" + graduado +
                '}';
    }
}
