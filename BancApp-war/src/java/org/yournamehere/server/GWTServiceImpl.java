/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.server;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ejb.ClientEntity;
import java.util.Collection;

import org.yournamehere.client.GWTService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
//import web.PostClient;
import webservice.BancWebInterface;
import webservice.BancWebservicesException;

/**
 *
 * @author younes
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {
    @EJB
    private BancWebInterface bancWebservices;
    @Override
    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        try {

                    //clientEntityFacade.create(e);
                    //
                    bancWebservices.ajouteClient("toto", "tata", "23","mail");
          } catch (BancWebservicesException ex) {
                  //Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return "Server says: " + s;
    }

    @Override
    public String ajouteClient(String nom,String Prenom, String age,String mail){
         try {

                    //clientEntityFacade.create(e);
                    //
                    bancWebservices.ajouteClient(nom, Prenom, age,mail);
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return "Client ajouté avec succés. ";
    }
     @Override
    public String ajouteCompte(String numClient,String type, String solde){
         try {

                    //clientEntityFacade.create(e);
                    //
                    bancWebservices.ajouteCompte(numClient, type, solde);
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return "Compte ajouté avec succès. ";
    }

 /* @Override
    public Collection<Client> chargeClients() {
       try {

                   //clientEntityFacade.create(e);
                   //
                  //return bancWebservices.chargeClients();
                  return null;
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;
    }
*/
    @Override
    public Collection<String> chargeClients() {
        try {

                   //clientEntityFacade.create(e);
                   //
                  return bancWebservices.chargeClients();
                  //return null;
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;

    }

    @Override
    public Boolean supprimeClient(String id) {
       try {

                   //clientEntityFacade.create(e);
                   //
                  return bancWebservices.supprimeClient(id);
                  //return null;
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return false;

    }

    @Override
    public Boolean supprimeCompte(String id) {
       try {

                   //clientEntityFacade.create(e);
                   //
                  return bancWebservices.supprimeCompte(id);
                  //return null;
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return false;

    }

    @Override
    public Collection<String> chargeComptes() {
        try {

                   //clientEntityFacade.create(e);
                   //
                  return bancWebservices.chargeComptes();
                  //return null;
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;
    }

    @Override
    public Collection<String> chargeClientsCrit(String id, String nom, String prenom, String age) {
          try {

                   //clientEntityFacade.create(e);
                   //
                  return bancWebservices.chargeClientsCrit(id,nom,prenom,age);
                  //return null;
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;

    }

    @Override
    public Collection<String> chargeComptesCrit(String numCompte, String type, String solde) {
        try {

                   //clientEntityFacade.create(e);
                   //
                  return bancWebservices.chargeComptesCrit(numCompte,type,solde);
                  //return null;
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;
    }

    @Override
    public Collection<String> chargeComptesByClient(String numClients) {
         try {

                   //clientEntityFacade.create(e);
                   //
                  return bancWebservices.chargeComptesByClient(numClients);
                  //return null;
          } catch (BancWebservicesException ex) {
                 // Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;
    }

    @Override
    public Boolean DebiterCompte(String numCompte, String valDebit) {
                 return bancWebservices.DebiterCompte(numCompte, valDebit);
    }

    @Override
    public Boolean CrediterCompte(String numCompte, String valDebit) {
                return bancWebservices.CrediterCompte(numCompte, valDebit);
    }

    @Override
    public Boolean TransfertCompte(String valTrans, String src, String dest) {
                return bancWebservices.TransfertCompte(valTrans, src, dest);
    }
    
    @Override
    public void postMail( String recipients[ ], String subject, String message , String from) //throws MessagingException
    {
        try //throws MessagingException
        {
            boolean debug = false;
            //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.live.com");
            // create some properties and get the default Session
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(debug);
            // create a message
            Message msg = new MimeMessage(session);
            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            // Optional : You can also set your custom headers in the Email if you Want
            msg.addHeader("MyHeaderName", "myHeaderValue");
            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(message, "text/plain");
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    
}
