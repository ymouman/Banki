/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author younes
 */
@Stateless
public class ClientEntityFacade extends AbstractFacade<ClientEntity> {
    @PersistenceContext(unitName = "BancApp-ejbPU2")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientEntityFacade() {
        super(ClientEntity.class);
    }

}
