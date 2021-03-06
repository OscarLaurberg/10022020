package facades;

import dto.MovieDTO;
import utils.EMF_Creator;
import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public FacadeExampleTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MovieFacade.getFacadeExample(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getFacadeExample(emf);
    }

//    @AfterAll
//    public static void tearDownClass() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
//            em.getTransaction().commit();
//        }finally{
//            em.close();
//        }
//    }
    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            String[] actorArray1 = new String[]{"Crash Bandicoot", "Tom Hanks", "Spyro"};
            String[] actorArray2 = new String[]{"Kim", "Trold", "Lille Skid"};
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
            facade.addMovie(1993, "Il Nombre Del Padre", actorArray1);
            facade.addMovie(2001, "bbb", actorArray2);
            facade.addMovie(2002, "bbb", actorArray2);
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetMovieCount() {
        assertEquals(3, facade.getMovieCount());
    }

    @Test
    public void testGetMovieByName() {
        String name = "Il Nombre Del Padre";
        List<Movie> movies = facade.getMovieByName(name);
        String result = movies.get(0).getName();
        assertEquals(name, result);
    }

    @Test
    public void testMovieByID() {
        String name = "Il Nombre Del Padre";
        List<Movie> movies = facade.getMovieByName(name);
        Movie movie1 = movies.get(0);
        Long id = movie1.getId();
        Movie movie2 = facade.getMovieByID(id.intValue());
        String result = movie2.getName();
        assertEquals(name, result);
    }

    @Test
    public void testAddMovie() {
        Long expected = 4L;
        String[] actorArray = new String[]{"Crash Bandicoot", "Tom Hanks", "Spyro"};
        facade.addMovie(1993, "Testfilm", actorArray);
        Long result = facade.getMovieCount();
        assertEquals(expected, result);
    }


}
