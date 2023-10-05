import tp2.dao.DAOEstudiante;
import tp2.entidades.Carrera;
import tp2.entidades.Estudiante;
import tp2.factory.FactoryEntityManager;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws Exception {

        // mysql dao factory
        FactoryEntityManager factory_entity_manager = FactoryEntityManager.getEntityManagerFactory("MYSQL");
        EntityManager entity_manager = factory_entity_manager.connect("d");

        // daos
        DAOEstudiante estudiantedao = factory_entity_manager.getEstudianteDAO(entity_manager);
        factory_entity_manager.getEstudianteDAO(entity_manager);

        // objeto estudiante

        Estudiante estudiante = new Estudiante("prueba", "1","12/09/1997" , "hombre", "232434", "Olavarria", "3453453", false, new ArrayList<Carrera>());
        estudiantedao.insertEstudiante(estudiante);


    }
}
