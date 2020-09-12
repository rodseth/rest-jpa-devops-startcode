package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public void deleteAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public long MovieCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long movieMeCount = (long) em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return movieMeCount;
        } finally {
            em.close();
        }
    }

    public Movie getMovieById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select m from Movie m where m.id = :id");
            query.setParameter("id", id);
            Movie movie = (Movie) query.getSingleResult();
            return movie;
        } finally {
            em.close();
        }

    }

    public List<Movie> getMovieByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select m from Movie m where m.title = :title");
            query.setParameter("title", title);
            List<Movie> movieList = query.getResultList();
            return movieList;
        } finally {
            em.close();
        }
    }

    public List<Movie> getAllMovies() {

        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Movie.getAll");
            List<Movie> movies = query.getResultList();
            return movies;
        } finally {
            em.close();
        }
    }

    public List<Movie> getMoviesByRelease(int released) {

        EntityManager em = emf.createEntityManager();
        try {
            Query tq = em.createQuery("SELECT m FROM Movie m WHERE m.released = :id");
            tq.setParameter("id", released);
            return tq.getResultList();
        } finally {
            em.close();
        }
    }

    void addMovie(int released, String title, String[] actors) {

        EntityManager em = emf.createEntityManager();
        Movie movie = new Movie(released, title, actors);
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Movie(2020, "Battle of the Studypoints", new String[]{"Nikolaj", "Jon", "Lars"}));
            em.persist(new Movie(2019, "The Dreamteam", new String[]{"Matt", "Pelle", "Benjamin"}));
            em.persist(new Movie(2021, "To Studypoint or not to Studypoint", new String[]{"Bornholm", "Lyngby"}));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
