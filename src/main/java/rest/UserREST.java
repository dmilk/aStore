/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import auth.AuthAccessElement;
import auth.AuthInfo;
import auth.AuthService;
import auth.Salt;
import entity.User;
import javax.annotation.security.PermitAll;
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
import session.UserFacade;
import session.UuidBean;

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

    @EJB
    UuidBean uuidBean;

    public UserREST() {
    }

    @POST
    @Path("signup")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(UserJson userJson) {
        System.out.println(userJson);
        Salt salt = userFacade.createUser(userJson.getFirstName(), userJson.getLastName(),
                userJson.getEmail(), userJson.getPhone());

        System.out.println("Signup = " + userJson);
        if (salt != null) {
            return Response.ok(salt).build();
        } else {
            System.err.println("email already exist");
            return Response.status(Response.Status.FORBIDDEN).entity("email already exist").build();
        }
    }

    @POST
    @Path("password")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setPassword(PasswordJson passwordJson) {
        System.out.println(passwordJson);
        if (userFacade.setUserPassword(passwordJson.getLogin(), passwordJson.getPassword())) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("add password fail").build();
        }
    }

    @GET
    @Path("salt")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalt(@QueryParam("email") String email) {

        System.out.println("getSalt = " + email);
        Salt salt = userFacade.getSalt(email);
        return Response.ok(salt).build();
    }

    @POST
    @Path("login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest request, AuthInfo authInfo) {
        System.out.println("authInfo = " + authInfo);

        AuthAccessElement authAccessElement = authService.login(authInfo);
        if (authAccessElement != null) {
            request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_TOKEN, authAccessElement.getAuthToken());
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("XX фиг вам!!!").build();
        }
//        return authAccessElement;
        return Response.ok(authAccessElement).build();
    }

    @GET
    @Path("info")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({"application/xml", "application/json"})
    @PermitAll
    public UserJson getUserInfo(@Context HttpServletRequest request) {
        System.out.println("*********** getUserInfo() ***********");
        String authToken = request.getHeader(AuthAccessElement.PARAM_AUTH_TOKEN);
        User user = userFacade.findByToken(authToken);
        if (user != null) {
            return new UserJson(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone());
        }
        return new UserJson();
    }

    @GET
    @Path("uuid")
    @PermitAll
    public String getUuid() {
        return uuidBean.getUuid();

    }

    public static class UserJson {

        private String firstName;
        private String lastName;
        private String email;
        private String phone;

        public UserJson() {
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

        @Override
        public String toString() {
            return "UserJson{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + '}';
        }
    }

    public static class PasswordJson {

        private String login;
        private String password;

        public PasswordJson() {
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "PasswordJson{" + "login=" + login + ", password=" + password + '}';
        }
    }

}
