package session;

import entity.SupportingDocument;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Notreal
 */
@Stateless
public class SupportingDocumentFacade extends AbstractFacade<SupportingDocument> {
    @PersistenceContext(unitName = "aStorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupportingDocumentFacade() {
        super(SupportingDocument.class);
    }
    
}
