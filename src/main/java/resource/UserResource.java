package resource;

import auth.AuthAccessElement;
import auth.AuthInfo;
import auth.Salt;
import entity.User;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import session.AuthService;
import session.UserFacade;
import session.UuidService;

/**
 *
 * @author Notreal
 */
@Path("user")
public class UserResource {
    
    @EJB
    AuthService authService;

    @EJB
    UserFacade userFacade;

    @EJB
    UuidService uuidBean;

    public UserResource() {
    }

    @POST
    @Path("signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(User user) {
        Salt salt = userFacade.createUser(user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPhone());

        if (salt != null) {
            return Response.ok(salt).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("email already exist").build();
        }
    }

    @POST
    @Path("password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setPassword(AuthInfo authInfo) {
        if (userFacade.setUserPassword(authInfo.getLogin(), authInfo.getPassword())) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("add password fail").build();
        }
    }

    @GET
    @Path("salt")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalt(@QueryParam("email") String email) {
        Salt salt = userFacade.getSalt(email);
        return Response.ok(salt).build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest request, AuthInfo authInfo) {
        AuthAccessElement authAccessElement = authService.login(authInfo);
        if (authAccessElement != null) {
            request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_TOKEN, authAccessElement.getAuthToken());
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("!").build();
        }
        return Response.ok(authAccessElement).build();
    }

    @GET
    @Path("info")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getUserInfo(@Context HttpServletRequest request) {
        String authToken = request.getHeader(AuthAccessElement.PARAM_AUTH_TOKEN);
        User user = userFacade.findByToken(authToken);
        return user;
    }
}
