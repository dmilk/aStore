package resource;

import entity.SupportingDocument;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import session.SupportingDocumentFacade;

/**
 *
 * @author Notreal
 */
@Path("document")
public class SupportingDocumentResource {
    
    @EJB 
    SupportingDocumentFacade supportingDocumentFacade;

    public SupportingDocumentResource() {
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<SupportingDocument> findAll() {
        return supportingDocumentFacade.findAll();
    }
}

