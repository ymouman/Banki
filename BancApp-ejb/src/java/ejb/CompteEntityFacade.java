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
 * @author ELHAJMIR
 */
@Stateless
public class CompteEntityFacade extends AbstractFacade<CompteEntity> {
    @PersistenceContext(unitName = "BancApp-ejbPU2")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteEntityFacade() {
        super(CompteEntity.class);
    }

}
