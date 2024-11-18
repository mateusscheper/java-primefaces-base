package scheper.mateus.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serial;
import java.io.Serializable;

@ApplicationScoped
public class BaseRepository implements Serializable {

    
    @Serial
    private static final long serialVersionUID = -2177241904083852057L;

    @PersistenceContext
    protected EntityManager entityManager;

    public <T> T find(Class<T> clazz, Object pk) {
        return entityManager.find(clazz, pk);
    }

    public <T> void persist(T entity) {
        entityManager.persist(entity);
    }

    public <T> void merge(T entity) {
        entityManager.merge(entity);
    }

    public void flush() {
        entityManager.flush();
    }

    public <T> void remove(T entity) {
        entityManager.remove(entity);
    }
}