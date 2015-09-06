/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import ejb.ClientEntity;
import ejb.CompteEntity;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author younes
 */
@Remote
public interface BancWebInterface {
    public Collection<String> chargeClients() throws BancWebservicesException;
    public Collection<String> chargeClientsCrit(String id,String nom,String prenom,String age) throws BancWebservicesException;
    public Collection<String> chargeComptes() throws BancWebservicesException;
    public Collection<String> chargeComptesCrit(String numCompte,String type,String solde) throws BancWebservicesException;
    public Collection<String> chargeComptesByClient(String numClient) throws BancWebservicesException;
    public Boolean DebiterCompte(String numCompte,String valDebit);
    public Boolean CrediterCompte(String numCompte,String valDebit);
    public Boolean TransfertCompte(String valTrans,String src,String dest);
    public ClientEntity chargeClient(String id) throws BancWebservicesException;
    public CompteEntity chargeCompte(String id) throws BancWebservicesException;
    public Long ajouteClient(String nom,String prenom,String age,String mail) throws BancWebservicesException;
    public Boolean supprimeClient(String id) throws BancWebservicesException;
    public Boolean supprimeCompte(String id) throws BancWebservicesException;
    public Long ajouteCompte(String numClient,String type,String solde) throws BancWebservicesException;
}
