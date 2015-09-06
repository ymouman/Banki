/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ejb.ClientEntity;

import java.util.Collection;
//import javax.mail.MessagingException;

/**
 *
 * @author younes
 */
@RemoteServiceRelativePath("gwtservice")
public interface GWTService extends RemoteService {
    public String myMethod(String s);
    public String ajouteClient(String nom,String Prenom, String age,String mail);
    public String ajouteCompte(String numClient,String type, String solde);
    public Collection<String> chargeClients();
    public Collection<String> chargeComptes();
    public Collection<String> chargeComptesCrit(String numCompte,String type,String solde) ;
    public Collection<String> chargeComptesByClient(String numClient);
    public Collection<String> chargeClientsCrit(String id,String nom,String prenom,String age) ;
    public Boolean supprimeClient(String id);
    public Boolean supprimeCompte(String id);
    public Boolean DebiterCompte(String numCompte,String valDebit);
    public Boolean CrediterCompte(String numCompte,String valDebit);
    public Boolean TransfertCompte(String valTrans,String src,String dest);
    public void postMail( String recipients[ ], String subject, String message , String from);// throws MessagingException;

}


