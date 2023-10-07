package tp2.factory;

import tp2.interfaces.EstudianteCarreraRepository;
import tp2.repositories.CarreraRepositoryImpl;
import tp2.repositories.EstudianteCarreraRepositoryImpl;
import tp2.repositories.EstudianteRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MySqlEntityManager extends FactoryEntityManager {

    private EntityManagerFactory emf;
    private EntityManager em;
    private static MySqlEntityManager instance;

    public MySqlEntityManager() {}

    public static MySqlEntityManager getInstance() throws Exception {
        if (instance == null) {
            instance = new MySqlEntityManager();
        }
        return instance;
    }

    public EntityManager connect(String persistance_name) throws Exception {

        try {
            this.emf = Persistence.createEntityManagerFactory(persistance_name);
            this.em = this.emf.createEntityManager();
            this.em.getTransaction().begin();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (this.em != null) {
            return this.em;
        } else {
            throw new Exception("No se creo la conexion");
        }
    }

    public void close() {
        this.emf.close();
        this.em.close();
    }

    @Override
    public EstudianteRepositoryImpl getEstudianteRepository(EntityManager em) throws Exception {
        return new EstudianteRepositoryImpl(em);
    }

    @Override
    public CarreraRepositoryImpl getCarreraRepository(EntityManager em) throws Exception {
        return new CarreraRepositoryImpl(em);
    }

    @Override
    public EstudianteCarreraRepositoryImpl getEstudianteCarreraRepository(EntityManager em) throws Exception {
        return new EstudianteCarreraRepositoryImpl(em);
    }


}
