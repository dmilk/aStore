package resource;

import entity.Route;
import java.util.List;
import javax.ejb.EJB;
import javax.validation.constraints.DecimalMin;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import session.RouteFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Path("route")
public class RouteResource {
    
    @EJB
    RouteFacade routeFacade;

    public RouteResource() {
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Route> findAll() {
        return routeFacade.findAll();
    }
}
