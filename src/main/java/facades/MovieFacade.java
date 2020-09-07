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
    private MovieFacade() {}
    
    
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
    public long MovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    public List<Movie> getAllMovies (){
        
        EntityManager em = emf.createEntityManager();
        try{
            Query tq = em.createQuery("SELECT m FROM Movie m");
            return tq.getResultList();
        }finally {
            em.close();
        }
    }
    
    public List<Movie> getMoviesByRelease(int released){
        
        EntityManager em = emf.createEntityManager();
        try{
            Query tq = em.createQuery("SELECT m FROM Movie m WHERE m.released = :id");
            tq.setParameter("id", released);
            return tq.getResultList();
        }finally{
            em.close();
        }
    }
    public Movie getMovieById (Long id){
        
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class, id);
        return movie;
    }

    void addMovie(int released, String title, String[] actors) {
        
        EntityManager em = emf.createEntityManager();
        Movie movie = new Movie(released,title,actors);
        try{
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
        }finally {
        em.close();
    }}

    public Movie getMovieByTitle(String title) {
        
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Movie m WHERE m.title = :title");
        q.setParameter("title", title);
        Movie m = (Movie) q.getSingleResult();
        return m;
        
    }


}
