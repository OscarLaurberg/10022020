package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final MovieFacade FACADE = MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieFromID(@PathParam("id") int id) {
        Movie movie = FACADE.getMovieByID(id);
        return GSON.toJson(movie);
    }

    @GET
    @Path("title/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieFromName(@PathParam("name") String name) {
        List<Movie> moviesFromName = FACADE.getMovieByName(name);
        if (moviesFromName.size() > 0){
        return GSON.toJson(moviesFromName);
    }else{
            return ("{\"msg\":\"Movie not found\"}");
        }
    }
    
    @Path("lotto/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String lotto(@PathParam("id") int id) {
        if(id!=5){
            throw new WebApplicationException("UUUPS");
        }
       return "{\"lotto\":{\"lottoId\":5,\"winning-numbers\":[2,45,34,23,7,5,3],\"winners\":[{\"winnerId\":23,\"numbers\":[2,45,34,23,3,5]},{\"winnerId\":54,\"numbers\":[52,3,12,11,18,22]}]}}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allMovieDTOs(){
        List<Movie> allMovies = FACADE.getAllMovies();
        return GSON.toJson(allMovies);
    
}
    
    @POST
    @Path("movie/add")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addMovies(String json){
     List<Movie> movies = GSON.fromJson(json, new TypeToken<List<Movie>>(){}.getType()); 
     FACADE.addMovies(movies);
    }
    
    
}
