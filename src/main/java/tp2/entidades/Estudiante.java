package tp2.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "estudiante")

public class Estudiante {
    @Id
    @Column(name = "DNI")
    private String dni;
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apelllido")
    private String apellido;

    @Column(name = "edad")
    private int edad;

    @Column(name = "genero")
    private String genero;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "LU")
    private String num_libreta;

    public Estudiante(){}

    public Estudiante(String documento, String nombre, String apellido, int edad, String genero, String ciudad, String libreta) {
        this.dni = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.num_libreta = libreta;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getDocumento() {
        return dni;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getNum_libreta() {
        return num_libreta;
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

    public void setFecha_nacimiento(int edad) {
        this.edad = edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDocumento(String documento) {
        this.dni = documento;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setNum_libreta(String num_libreta) {
        this.num_libreta = num_libreta;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", documento='" + dni + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", num_libreta='" + num_libreta + '\'' +
                '}';
    }
}
