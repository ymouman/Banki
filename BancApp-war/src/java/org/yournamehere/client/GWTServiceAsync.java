/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
//import ejb.ClientEntity;
import java.util.Collection;

/**
 *
 * @author younes
 */
public interface GWTServiceAsync {
    public void myMethod(String s, AsyncCallback<String> callback);
    public void ajouteClient(String nom,String Prenom, String age,String mail,AsyncCallback<String> callback);
    public void ajouteCompte(String numClient, String type, String solde, AsyncCallback<String> asyncCallback);
    public void chargeClients(AsyncCallback<Collection<String>> asyncCallback);

    public void supprimeClient(String id, AsyncCallback<Boolean> asyncCallback);

    public void chargeComptes(AsyncCallback<Collection<String>> asyncCallback);
;

    public void supprimeCompte(String id, AsyncCallback<Boolean> asyncCallback);

    public void chargeClientsCrit(String id, String nom, String prenom, String age, AsyncCallback<Collection<String>> asyncCallback);

    public void chargeComptesCrit(String numCompte, String type, String solde, AsyncCallback<Collection<String>> asyncCallback);

    public void chargeComptesByClient(String numClient, AsyncCallback<Collection<String>> asyncCallback);

    public void DebiterCompte(String numCompte, String valDebit, AsyncCallback<Boolean> asyncCallback);

    public void CrediterCompte(String numCompte, String valDebit, AsyncCallback<Boolean> asyncCallback);

    public void TransfertCompte(String valTrans, String src, String dest, AsyncCallback<Boolean> asyncCallback);

    public void postMail(java.lang.String[] recipients, String subject, String message, String from, AsyncCallback<Void> asyncCallback);
}
