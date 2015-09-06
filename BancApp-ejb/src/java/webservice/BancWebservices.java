

package webservice;

import ejb.ClientEntity;
import ejb.ClientEntityFacade;
import ejb.CompteEntity;
import ejb.CompteEntityFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author younes
 */
@WebService()
@Stateless()
public class BancWebservices implements BancWebInterface{
    @PersistenceContext
    private EntityManager em;
    @EJB
    private CompteEntityFacade compteEntityFacade;
    @EJB
    private ClientEntityFacade clientEntityFacade;


    /**
     * Web service operation
     */
    @WebMethod(operationName = "chargeClients")
    @WebResult(name="client")
    @Override
    public Collection<String> chargeClients() throws BancWebservicesException{
        try {
            String client="";
            Collection<String> listClients= new ArrayList<String>();

            List myclients=clientEntityFacade.findAll();
            for (Iterator it = myclients.iterator(); it.hasNext();) {
                ClientEntity elem =  (ClientEntity) it.next();
                //client.clear();
                client="";
                client+=String.valueOf(elem.getId())+"-";
                client+=elem.getNom()+"-";
                client+=elem.getPrenom()+"-";
                client+=elem.getMail()+"-";
                client+=elem.getAge();

                listClients.add(client);

                
            }
            return listClients;//em.createQuery("select object(p) from ClientEntity as p").getResultList();
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Erreur de connexion vers la base de données");
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "chargeClientsCrit")
    @WebResult(name="clientcrit")
    @Override
    public Collection<String> chargeClientsCrit(@WebParam(name = "id")
    String id,@WebParam(name = "nom")
    String nom, @WebParam(name = "prenom")
    String prenom, @WebParam(name = "age")
    String age) throws BancWebservicesException{
        try {
            String client="";
            Collection<String> listClients= new ArrayList<String>();
            if(id.equals("")){
                    id="0";
             }
            List myclients=clientEntityFacade.findby(Long.parseLong(id), nom, prenom, age, null);
            for (Iterator it = myclients.iterator(); it.hasNext();) {
                ClientEntity elem =  (ClientEntity) it.next();
                //client.clear();
                client="";
                client+=String.valueOf(elem.getId())+"-";
                client+=elem.getNom()+"-";
                client+=elem.getPrenom()+"-";
                client+=elem.getMail()+"-";
                client+=elem.getAge();

                listClients.add(client);


            }
            return listClients;//em.createQuery("select object(p) from ClientEntity as p").getResultList();
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Erreur de connexion vers la base de données");
        }
    }
   /**
     * Web service operation
     */
    @WebMethod(operationName = "chargeComptes")
    @WebResult(name="compte")
    @Override
    public Collection<String> chargeComptes() throws BancWebservicesException{
        try {
            String compte="";
            Collection<String> listComptes= new ArrayList<String>();

            List mycomptes=compteEntityFacade.findAll();
            for (Iterator it = mycomptes.iterator(); it.hasNext();) {
                CompteEntity elem =  (CompteEntity) it.next();
                //client.clear();
                compte="";
                compte+=String.valueOf(elem.getNumCompte())+"-";
                compte+=elem.getSolde()+"-";
                compte+=elem.getType()+"-";
                ClientEntity prop=elem.getClient();
                compte+=prop.getNom()+" "+prop.getPrenom()+"-";
                compte+=prop.getMail()+"-";
                compte+=prop.getAge();

                listComptes.add(compte);


            }
            return listComptes;//em.createQuery("select object(p) from ClientEntity as p").getResultList();
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Erreur de connexion vers la base de données");
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "chargeComptesCrit")
    @WebResult(name="compte")
    @Override
    public Collection<String> chargeComptesCrit(@WebParam(name = "numCompte")
    String numCompte, @WebParam(name = "type")
    String type, @WebParam(name = "solde")
    String solde) throws BancWebservicesException{
        try {
            String compte="";
            Collection<String> listComptes= new ArrayList<String>();
            if(numCompte.equals("")){
                    numCompte="0";
                }

            List mycomptes=compteEntityFacade.find_compte_by(Long.parseLong(numCompte), solde, type, null);
            for (Iterator it = mycomptes.iterator(); it.hasNext();) {
                CompteEntity elem =  (CompteEntity) it.next();
                //client.clear();
                compte="";
                compte+=String.valueOf(elem.getNumCompte())+"-";
                compte+=elem.getSolde()+"-";
                compte+=elem.getType()+"-";
                ClientEntity prop=elem.getClient();
                compte+=prop.getNom()+" "+prop.getPrenom()+"-";
                compte+=prop.getMail()+"-";
                compte+=prop.getAge();

                listComptes.add(compte);


            }
            return listComptes;//em.createQuery("select object(p) from ClientEntity as p").getResultList();
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Erreur de connexion vers la base de données");
        }
    }

   /**
     * Web service operation
     */
    @WebMethod(operationName = "chargeComptesByClient")
    @WebResult(name="comptecl")
    @Override
    public Collection<String> chargeComptesByClient(@WebParam(name = "numCliente")
    String numClient) throws BancWebservicesException{
        try {
            String compte="";
            Collection<String> listComptes= new ArrayList<String>();
            if(numClient.equals("")){
                    numClient="0";
                }

            List mycomptes=compteEntityFacade.findcompteByClient(Long.parseLong(numClient));

            for (Iterator it = mycomptes.iterator(); it.hasNext();) {
                CompteEntity elem =  (CompteEntity) it.next();
                //client.clear();
                compte="";
                compte+=String.valueOf(elem.getNumCompte())+"-";
                compte+=elem.getSolde()+"-";
                compte+=elem.getType()+"-";
                ClientEntity prop=elem.getClient();
                compte+=prop.getNom()+" "+prop.getPrenom()+"-";
                compte+=prop.getMail()+"-";
                compte+=prop.getAge();

                listComptes.add(compte);


            }
            return listComptes;
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Erreur de connexion vers la base de données");
        }
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "chargeClient")
    @WebResult(name="client")
    @Override
    public ClientEntity chargeClient(@WebParam(name = "id")
    String id) throws BancWebservicesException {
        try {
            ClientEntity client =clientEntityFacade.find(Long.valueOf(id));
            //ClientEntity client = em.find(ClientEntity.class, Long.valueOf(id));
            if ( client == null ) {
                throw new BancWebservicesException("l'identifiant client '"+id+"' n'existe pas");
            }
            return client;
        } catch ( BancWebservicesException ex ) {
            throw ex;
        } catch ( NumberFormatException ex ) {
            throw new BancWebservicesException("L'id client doit être un nombre");
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Erreur de connection vers la base de données");
        }
    }

     /**
     * Web service operation
     */
    @WebMethod(operationName = "DebiterCompte")
    @WebResult(name="resuldebit")
    @Override
    public Boolean DebiterCompte(@WebParam(name = "numCompte")
    String numCompte,@WebParam(name = "valDebit")
    String valDebit){

              compteEntityFacade.debiter(valDebit, numCompte);
              return true;

    }

   /**
     * Web service operation
     */
    @WebMethod(operationName = "CrediterCompte")
    @WebResult(name="resulcredit")
    @Override
    public Boolean CrediterCompte(@WebParam(name = "numCompte")
    String numCompte,@WebParam(name = "valCredit")
    String valDebit){

              compteEntityFacade.crediter(valDebit, numCompte);
              return true;

    }

  /**
     * Web service operation
     */
    @WebMethod(operationName = "TransfertCompte")
    @WebResult(name="resultrans")
    @Override
    public Boolean TransfertCompte(@WebParam(name = "valTrans")
    String valTrans,@WebParam(name = "src")
    String src,@WebParam(name = "dest")
    String dest){

              compteEntityFacade.transfert(valTrans, src, dest);
              return true;

    }

   /**
     * Web service operation
     */

    @WebMethod(operationName = "chargeCompte")
    @WebResult(name="compte")
    @Override
    public CompteEntity chargeCompte(@WebParam(name = "id")
    String id) throws BancWebservicesException {
        try {
            CompteEntity compte =compteEntityFacade.find(Long.valueOf(id));
            //ClientEntity client = em.find(ClientEntity.class, Long.valueOf(id));
            if ( compte == null ) {
                throw new BancWebservicesException("le numéro de copte  '"+id+"' n'existe pas");
            }
            return compte;
        } catch ( BancWebservicesException ex ) {
            throw ex;
        } catch ( NumberFormatException ex ) {
            throw new BancWebservicesException("Le num de compte doit être un nombre");
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Erreur de connection vers la base de données");
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ajouteClient")
    @WebResult(name="id")
    @Override
    public Long ajouteClient(@WebParam(name = "nom")
    String nom, @WebParam(name = "prenom")
    String prenom, @WebParam(name = "age")
    String age, @WebParam(name = "mail")
    String mail) throws BancWebservicesException {
        try {
            //int ageInt = Integer.parseInt(age);
            //if ( ageInt<=0 )
              //  throw new NumberFormatException();
            ClientEntity client = new ClientEntity();
            client.setAge(age);
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setMail(mail);

            em.persist(client);
            return client.getId();
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Erreur de connexion vers la base de données !!");
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "supprimeClient")
    @WebResult(name="erased")
    @Override
    public Boolean supprimeClient(@WebParam(name = "id")
    String id) throws BancWebservicesException {
        try {
            clientEntityFacade.remove(this.chargeClient(id));
            //em.remove();
            return true;
        } catch ( BancWebservicesException ex ) {
            throw ex;
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Error connecting to database");
        }
    }

        /**
     * Web service operation
     */
    @WebMethod(operationName = "supprimeCompte")
    @WebResult(name="cpterased")
    @Override
    public Boolean supprimeCompte(@WebParam(name = "id")
    String id) throws BancWebservicesException {
        try {
            compteEntityFacade.remove(this.chargeCompte(id));
            //em.remove();
            return true;
        } catch ( BancWebservicesException ex ) {
            throw ex;
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Error connecting to database");
        }
    }

    
    /**
     * Web service operation
     */
    
    @WebMethod(operationName = "AjouteCompte")
    @WebResult(name="resultnb")
    @Override
    public Long ajouteCompte(@WebParam(name = "numClient")
    String numClient, @WebParam(name = "type")
    String type, @WebParam(name = "solde")
    String solde) throws BancWebservicesException {
        try {
            CompteEntity e = new CompteEntity();
                //e.setNumCompte(numCompte);
                e.setSolde(solde);
                e.setType(type);
                ClientEntity cl=clientEntityFacade.find(Long.parseLong(numClient));
                e.setClient(cl);
                compteEntityFacade.create(e);
            return e.getNumCompte();
        
        } catch ( Exception ex ) {
            throw new BancWebservicesException("Error connecting to database");
        }
    }

    
}
