package filter;

import auth.AuthAccessElement;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 *
 * @author Notreal
 */
public class NewCrossOriginResourceSharingFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        String headers = "Content-Type";
        headers = headers + ", " + AuthAccessElement.PARAM_AUTH_TOKEN;
        response.getHeaders().putSingle("Access-Control-Allow-Headers", headers);
    }

}
