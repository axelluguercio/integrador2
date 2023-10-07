package tp2.factory;

import tp2.interfaces.EstudianteCarreraRepository;
import tp2.repositories.CarreraRepositoryImpl;
import tp2.repositories.EstudianteCarreraRepositoryImpl;
import tp2.repositories.EstudianteRepositoryImpl;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class FactoryEntityManager  {

    EntityManagerFactory emf;

    EntityManager em;

    public abstract EntityManager connect(String persistance_name) throws Exception;

    public abstract void close();

    public abstract EstudianteRepositoryImpl getEstudianteRepository(EntityManager em) throws Exception;

    public abstract CarreraRepositoryImpl getCarreraRepository(EntityManager em) throws Exception;

    public abstract EstudianteCarreraRepositoryImpl getEstudianteCarreraRepository(EntityManager em) throws Exception;

    public static FactoryEntityManager getEntityManagerFactory (String factoryType) throws Exception {
        switch (factoryType) {
            case "MYSQL":
                return MySqlEntityManager.getInstance();
            default:
                throw new IllegalArgumentException("Tipo de fábrica no válido: " + factoryType);
        }
    }


}
