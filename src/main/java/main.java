import tp2.dao.DAOCarrera;
import tp2.dao.DAOEstudiante;
import tp2.entidades.Carrera;
import tp2.entidades.Estudiante;
import tp2.factory.FactoryEntityManager;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) throws Exception {

        // mysql dao factory
        FactoryEntityManager factory_entity_manager = FactoryEntityManager.getEntityManagerFactory("MYSQL");
        EntityManager entity_manager = factory_entity_manager.connect("integrador2");

        // daos
        DAOEstudiante estudiantedao = factory_entity_manager.getEstudianteDAO(entity_manager);
        factory_entity_manager.getEstudianteDAO(entity_manager);
        DAOCarrera carreradao = factory_entity_manager.getCarreraDAO(entity_manager);


        // objeto estudiante
        //Estudiante estudiante = new Estudiante("prueba", "1","12/09/1997" , "hombre", "232434", "Olavarria", "3453453", false, new ArrayList<Carrera>());
        //estudiantedao.insertEstudiante(estudiante);

        //objeto carrera
        //Carrera carrera1 = new Carrera("Ingenieria en Sistemas de Informacion", 5, new ArrayList<Estudiante>());
        //carreradao.insertCarrera(carrera1);

        //lista estudiantes
        List<Estudiante> estudiantes=  estudiantedao.obtenerTodosLosEstudiantesOrdenados();
        estudiantedao.matricularEstudianteEnCarrera(estudiantes.get(0), carreradao.obtenerCarreraPorNombre("Ingenieria en Sistemas de Informacion"));

        List<Carrera> est1Carreras = estudiantes.get(0).getCarreras();

        for (Carrera carrera : est1Carreras) {
            System.out.println(carrera.getNombre());
        }
        // close
        factory_entity_manager.close();

        // print


    }
}
