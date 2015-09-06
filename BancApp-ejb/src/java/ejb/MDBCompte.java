/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author younes
 */
@MessageDriven(mappedName = "jms/jmsCompte", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class MDBCompte implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    @PersistenceContext(unitName = "BancApp-ejbPU2")
    private EntityManager em;
    public MDBCompte() {
    }

    public void onMessage(Message message) {
             ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                CompteEntity e = (CompteEntity) msg.getObject();
                save(e);
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }

     public void save(Object object) {
        //em.getTransaction().begin();
        em.persist(object);
        //em.getTransaction().commit();
    }
}
