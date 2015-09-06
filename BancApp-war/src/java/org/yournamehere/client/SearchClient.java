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
 import com.google.gwt.user.client.ui.HTMLPanel;
 import com.google.gwt.user.client.ui.Label;
 import java.util.Collection;
 import java.util.Iterator;
/*import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import web.PostClient;
import webservice.BancWebInterface;
import webservice.BancWebservicesException;
*/
 public class SearchClient extends LayoutContainer {

   private VerticalPanel vp;
   private Label lblServerReply;
   private FieldSet fieldSet;
   private FormData formData;
   private HTMLPanel htmlpanel;
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
     form2.setHeading("Rechercher des clients");
     form2.setWidth(600);
     form2.setHeight(500);
     form2.setLayout(new FlowLayout());


     String htmltxt="<table align='center' border='0'><tr><td id='cuerpo'></td></tr></table><br><br>";
     htmlpanel =new HTMLPanel(htmltxt);

     fieldSet = new FieldSet();
     fieldSet.setHeading("Clès de recherche");
     fieldSet.setCheckboxToggle(true);
     

     FormLayout layout = new FormLayout();
     layout.setLabelWidth(75);
     fieldSet.setLayout(layout);



     final TextField<String> Id = new TextField<String>();
     Id.setFieldLabel("Identifiant");
     Id.setAllowBlank(true);
     fieldSet.add(Id, formData);

     final TextField<String> Nom = new TextField<String>();
     Nom.setFieldLabel("Nom");
     Nom.setAllowBlank(true);
     fieldSet.add(Nom, formData);

     final TextField<String> Prenom = new TextField<String>();
     Prenom.setFieldLabel("Prenom");
     Prenom.setAllowBlank(true);
     fieldSet.add(Prenom, formData);

     final TextField<String> age = new TextField<String>();
     age.setFieldLabel("Age");
     age.setAllowBlank(true);
     fieldSet.add(age, formData);


     form2.add(fieldSet);

      final AsyncCallback<Collection<String>> callback = new AsyncCallback<Collection<String>>() {

            @Override
            public void onSuccess(Collection<String> result) {
                HTMLPanel tabclients;

                String html="<table align='center' border='1'>";
                html+= "<tr><td><b>Identifiant</b></td><td><b>Nom</b></td><td><b>Prenom</b></td><td><b>Age</b></td><td><b>Mail</b></td></tr>";
                String cl="";
                for (Iterator it = result.iterator(); it.hasNext();) {
                  cl =  (String) it.next();

                  //Window.alert(cl);
                  String [] tab=cl.split("-");
                  html+= "<tr><td>"+tab[0]+"</td><td>"+tab[1]+"</td><td>"+tab[2]+"</td><td>"+tab[4]+"</td><td>"+tab[3]+"</td></tr>";


                }
                html+= "</table>";
                tabclients=new HTMLPanel(html);
                htmlpanel.clear();
                htmlpanel.add(tabclients, "cuerpo");

            }

            @Override
            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed");
            }
        };
     lblServerReply=new Label();

     form2.setButtonAlign(HorizontalAlignment.CENTER);
     Button save=new Button("Rechercher");
     Button cancel=new Button("Cancel");
     save.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){

                
                if(fieldSet.isExpanded()){
                       //Window.alert(Id.getValue().toString()+" | "+Nom.getValue().toString()+" | "+Prenom.getValue().toString()+" | "+age.getValue().toString());
                       getService().chargeClientsCrit((Id.getValue()==null)?"":Id.getValue().toString(),(Nom.getValue() == null)?"":Nom.getValue().toString(), (Prenom.getValue() == null)?"":Prenom.getValue().toString(), (age.getValue() == null)?"":age.getValue().toString(), callback);
                    
                }else{
                       getService().chargeClients(callback);
                }

           }
        });

     form2.addButton(save);
     form2.add(htmlpanel);


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