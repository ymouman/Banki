/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.client;



// import com.extjs.gxt.samples.resources.client.TestData;
 //import com.extjs.gxt.samples.resources.client.model.Stock;

 import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
// import com.extjs.gxt.samples.resources.client;

 import com.extjs.gxt.ui.client.widget.LayoutContainer;
 import com.extjs.gxt.ui.client.widget.VerticalPanel;
 import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

 import com.extjs.gxt.ui.client.widget.form.FieldSet;
 import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
 import com.extjs.gxt.ui.client.widget.form.TextField;

 import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
 import com.extjs.gxt.ui.client.widget.layout.FormData;
 import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
 import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/*import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import web.PostClient;
import webservice.BancWebInterface;
import webservice.BancWebservicesException;
*/
 public class AddCompte extends LayoutContainer {

   private VerticalPanel vp;
   private Label lblServerReply;
   //private ComboBox combo;
   private FormData formData;
   private ListBox lst;
   //@EJB
   //private BancWebInterface bancWebservices;

   @Override
   protected void onRender(Element parent, int index) {
     super.onRender(parent, index);
     formData = new FormData("-20");
     vp = new VerticalPanel();
     vp.setSpacing(10);
     //createForm1();
     createForm2();
     add(vp);
   }

   private void createForm2() {
     FormPanel form2 = new FormPanel();
     form2.setFrame(true);
     form2.setHeading("Créer un compte");
     form2.setWidth(500);
     form2.setHeight(350);
     form2.setLayout(new FlowLayout());

     FieldSet fieldSet = new FieldSet();
     fieldSet.setHeading("Données du compte");
     //fieldSet.setCheckboxToggle(true);

     FormLayout layout = new FormLayout();
     layout.setLabelWidth(75);
     fieldSet.setLayout(layout);

     
     String htmltxt="<table align='center' border='0'><tr><td id='a'></td><td id='b'></td></tr></table><br><br><br>";
     HTMLPanel htmlpanel =new HTMLPanel(htmltxt);
     htmlpanel.add(new Label("Compte pour le client"), "a");
     
     lst=new ListBox();
     getClients();
     htmlpanel.add(lst, "b");
     fieldSet.add(htmlpanel, formData);
     
    /*
    ListStore<State> states = new ListStore<State>();
    states.add(getStates());

     final ComboBox<State> combo1 = new ComboBox<State>();
    combo1.setEmptyText("Select a state...");
    combo1.setDisplayField("abbr");
    combo1.setWidth(150);
    combo1.setStore(states);
    combo1.setTypeAhead(true);
    combo1.setTriggerAction(TriggerAction.ALL);
    vp.add(combo1);
    */

     
     
     final TextField<String> type = new TextField<String>();
     type.setFieldLabel("Type compte");
     type.setAllowBlank(false);
     fieldSet.add(type, formData);
     
     final TextField<String> solde = new TextField<String>();
     solde.setFieldLabel("Solde");
     solde.setAllowBlank(false);
     fieldSet.add(solde, formData);

     //fieldSet.add(new GridExample(),formData);



     form2.add(fieldSet);

     final AsyncCallback<String> callback = new AsyncCallback<String>() {

            @Override
            public void onSuccess(String result) {
                lblServerReply.setText(result);
            }

            @Override
            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed");
            }
        };
     lblServerReply=new Label();

     final String numClient="1";

     form2.setButtonAlign(HorizontalAlignment.CENTER);
     Button save=new Button("Save");
     Button cancel=new Button("Cancel");
     save.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){

                String msgerror="";
                /*
                if(Nom.getValue()==null){
                    msgerror=msgerror.concat("- Champs Nom du client est vide !\n");
                }else if(Nom.getValue().toString().isEmpty()){
                    msgerror=msgerror.concat("- Champs Nom vide !\n");
                }
                 * */


                if(type.getValue()==null){
                        msgerror=msgerror.concat("- Champs type du compte est vide !\n");
                }else if(type.getValue().toString().isEmpty()){
                        msgerror=msgerror.concat("- Champs type du compte vide !\n");
                }

                String valeur;

                if(solde.getValue()==null){
                    msgerror=msgerror.concat("- le champ solde est vide!\n");
                }
                else{
                    valeur=solde.getValue().toString();
                    float intvalue=0;
                    try{
                     intvalue=Float.parseFloat(valeur);
                    }catch(NumberFormatException ex){
                        msgerror=msgerror.concat("- le champ solde est invalide  !\n");
                    }

                }

                String id="";
                String nom="";
                String prenom="";

                String[] tab=lst.getValue(lst.getSelectedIndex()).split("-");
                id=tab[0];
                nom=tab[1];
                prenom=tab[2];
                
                
                if(msgerror == null ? "" != null : !msgerror.equals("")){
                    Window.alert(msgerror);
                }else {
                    getService().ajouteCompte(id, type.getValue().toString(), solde.getValue().toString() , callback);
                }

           }
        });

        // Create an asynchronous callback to handle the result.



     form2.addButton(save);
     form2.addButton(cancel);


     form2.add(lblServerReply);
     vp.add(form2);
     //vp.add(new GWTServiceUsageExample());
   }


    public static GWTServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(GWTService.class);
    }

    public void getClients() {
    
    final AsyncCallback<Collection<String>> callback1 = new AsyncCallback<Collection<String>>() {


            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Erreur RPC: rechargement des clients interrompu !!");
            }

            @Override
            public void onSuccess(Collection<String> result) {
                //Window.alert("chargement des clients hhhhhh "+String.valueOf(result.size()));
                String cl="";
                for (Iterator it = result.iterator(); it.hasNext();) {
                  cl =  (String) it.next();
                                
                  //Window.alert(cl);

                  lst.addItem(cl);
                 
                }
            }



        };

        getService().chargeClients(callback1);

  }

 }/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

