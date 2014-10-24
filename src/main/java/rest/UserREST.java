/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import auth.AuthAccessElement;
import auth.AuthInfo;
import auth.AuthService;
import entity.User;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import session.UserFacade;

/**
 *
 * @author Notreal
 */
@Path("user")
public class UserREST {

    @EJB
    AuthService authService;

    @EJB
    UserFacade userFacade;

    public UserREST() {
    }

    @POST
    @Path("login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest request, AuthInfo authInfo) {
//    public AuthAccessElement login(@Context HttpServletRequest request, AuthInfo authInfo) {
        System.out.println("authInfo = " + authInfo);
        AuthAccessElement authAccessElement = authService.login(authInfo);
        if (authAccessElement != null) {
            request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_ID, authAccessElement.getAuthId());
            request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_TOKEN, authAccessElement.getAuthToken());
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("XXX хуй вам!!!").build();
        }
//        return authAccessElement;
        return Response.ok(authAccessElement).build();
    }

    @GET
    @Path("info")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({"application/xml", "application/json"})
    @PermitAll
    public UserJson getUserInfo(@Context HttpServletRequest request) {
        System.out.println("*********** getUserInfo() ***********");
        String authId = request.getHeader(AuthAccessElement.PARAM_AUTH_ID);
        String authToken = request.getHeader(AuthAccessElement.PARAM_AUTH_TOKEN);
        User user = userFacade.findByAuthIdAndAuthToken(authId, authToken);
        if (user != null) {
            return new UserJson(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone());
        }
        return new UserJson();
    }

    public static class UserJson {

        private String firstName;
        private String lastName;
        private String email;
        private String phone;

        public UserJson() {
            //this.firstName = "";
            this.lastName = "anonymous";
            //this.email = "2";
            //this.phone = "3";
        }

        public UserJson(String firstName, String lastName, String email, String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
        
        


    }

}
