/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.client;



 import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

 import com.extjs.gxt.ui.client.widget.LayoutContainer;
 import com.extjs.gxt.ui.client.widget.VerticalPanel;
 import com.extjs.gxt.ui.client.widget.button.Button;

 import com.extjs.gxt.ui.client.widget.form.FieldSet;
 import com.extjs.gxt.ui.client.widget.form.FormPanel;
 import com.extjs.gxt.ui.client.widget.form.TextField;

 import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
 import com.extjs.gxt.ui.client.widget.layout.FormData;
 import com.extjs.gxt.ui.client.widget.layout.FormLayout;
 import com.google.gwt.core.client.GWT;
 import com.google.gwt.user.client.Element;
 import com.google.gwt.user.client.Window;
 import com.google.gwt.user.client.rpc.AsyncCallback;
 import com.google.gwt.user.client.ui.Label;
/*import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import web.PostClient;
import webservice.BancWebInterface;
import webservice.BancWebservicesException;
*/
 public class AddClient extends LayoutContainer {

   private VerticalPanel vp;
   private Label lblServerReply;

   private FormData formData;
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
     form2.setHeading("Ajouter un client");
     form2.setWidth(350);
     form2.setHeight(350);
     form2.setLayout(new FlowLayout());

     FieldSet fieldSet = new FieldSet();
     fieldSet.setHeading("Informations du client");
     //fieldSet.setCheckboxToggle(true);

     FormLayout layout = new FormLayout();
     layout.setLabelWidth(75);
     fieldSet.setLayout(layout);

     final TextField<String> Nom = new TextField<String>();
     Nom.setFieldLabel("Nom");
     Nom.setAllowBlank(false);
     fieldSet.add(Nom, formData);

     final TextField<String> Prenom = new TextField<String>();
     Prenom.setFieldLabel("Prenom");
     Prenom.setAllowBlank(false);
     fieldSet.add(Prenom, formData);

     final TextField<String> age = new TextField<String>();
     age.setFieldLabel("Age");
     age.setAllowBlank(false);
     fieldSet.add(age, formData);
    
     final TextField<String> email = new TextField<String>();
     email.setFieldLabel("Email");
     email.setAllowBlank(false);
     fieldSet.add(email, formData);

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
                if(Nom.getValue()==null){
                    msgerror=msgerror.concat("- Champs Nom vide !\n");
                }else if(Nom.getValue().toString().isEmpty()){
                    msgerror=msgerror.concat("- Champs Nom vide !\n");
                }
                if(Prenom.getValue()==null){
                        msgerror=msgerror.concat("- Champs Prenom vide !\n");
                }else if(Prenom.getValue().toString().isEmpty()){
                        msgerror=msgerror.concat("- Champs Prenom vide !\n");
                }

                String valeur;

                if(age.getValue()==null){
                    msgerror=msgerror.concat("- le champ age est vide!\n");
                }
                else{
                    valeur=age.getValue().toString();
                    int intvalue=0;
                    try{
                     intvalue=Integer.parseInt(valeur);
                    }catch(NumberFormatException ex){
                        msgerror=msgerror.concat("- le champ age est invalide  !\n");
                    }

                }
                
                
                if(email.getValue()==null){
                    msgerror=msgerror.concat("- mail vide  !\n");
                }else{
                    valeur=email.getValue().toString();
                    int i=valeur.indexOf('@');
                    if(i<0){
                       msgerror=msgerror.concat("- mail invalide  !\n");
                    }
                }
                
                if(msgerror == null ? "" != null : !msgerror.equals("")){
                    Window.alert(msgerror);
                }else {
                    getService().ajouteClient(Nom.getValue().toString(), Prenom.getValue().toString(), age.getValue().toString(), email.getValue().toString(), callback);
                }
                
           }
        });

     form2.addButton(save);
     form2.addButton(cancel);

     
     form2.add(lblServerReply);
     vp.add(form2);
     
   }


    public static GWTServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(GWTService.class);
    }
 }