/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import auth.Salt;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import session.ReportManager;

/**
 *
 * @author Notreal
 */
@Stateless
@Path("test")
public class TestREST {

    @EJB
    ReportManager reportManager;

    public TestREST() {
    }

    @GET
    @Path("x")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response textExcel() throws FileNotFoundException, MessagingException, IOException {
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        reportManager.generateExcel(baos);
        byte[] data = baos.toByteArray();
        baos.close();
        ResponseBuilder response = Response.ok(data);
        response.header("Content-Disposition", "attachment; filename=excel_from_server.xlsx");
        return response.build();
    }

    @GET
    @Path("t")
//    @Produces({"application/xml", "application/json"})
    public String testPath() {
        return "new UserJson()";
    }

    @GET
    @Path("s")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalt() {
        Salt salt = new Salt();
        return Response.ok(salt).build();

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test(@Context HttpServletRequest request) {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        if (request.getUserPrincipal() != null) {
            builder.add("UserPrincipal", request.getUserPrincipal().toString());
        }

        builder.add("Header", "");
        Enumeration<String> header = request.getHeaderNames();
        while (header.hasMoreElements()) {
            String headerName = header.nextElement();
            builder.add(headerName, request.getHeader(headerName));
        }

        String testHeader = request.getHeader("xxx-auth-id");
        if (testHeader != null) {
            System.out.println("\n\n\n" + testHeader + "\n\n\n");
        }

        return builder.build().toString();
    }

}
