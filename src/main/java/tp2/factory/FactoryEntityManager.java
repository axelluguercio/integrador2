package tp2.factory;

import tp2.dao.DAOCarrera;
import tp2.dao.DAOEstudiante;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class FactoryEntityManager  {

    EntityManagerFactory emf;

    EntityManager em;

    public abstract EntityManager connect(String persistance_name) throws Exception;

    public abstract void close();

    public abstract DAOEstudiante getEstudianteDAO(EntityManager em) throws Exception;

    public abstract DAOCarrera getCarreraDAO(EntityManager em) throws Exception;

    public static FactoryEntityManager getEntityManagerFactory (String factoryType) throws Exception {
        switch (factoryType) {
            case "MYSQL":
                return MySqlEntityManager.getInstance();
            default:
                throw new IllegalArgumentException("Tipo de fábrica no válido: " + factoryType);
        }
    }


}
