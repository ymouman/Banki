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
 public class SearchCompte extends LayoutContainer {

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
     form2.setHeading("Ajouter un client");
     form2.setWidth(600);
     form2.setHeight(500);
     form2.setLayout(new FlowLayout());


     String htmltxt="<br><br><br><table align='center' border='0'><tr><td id='cuerpoc'></td></tr></table><br><br>";
     htmlpanel =new HTMLPanel(htmltxt);

     fieldSet = new FieldSet();
     fieldSet.setHeading("Clès de recherche");
     fieldSet.setCheckboxToggle(true);


     FormLayout layout = new FormLayout();
     layout.setLabelWidth(75);
     fieldSet.setLayout(layout);
/*
     final TextField<String> Id = new TextField<String>();
     Id.setFieldLabel("Identifiant du propriétaire");
     Id.setAllowBlank(true);
     fieldSet.add(Id, formData);
*/

     final TextField<String> Num = new TextField<String>();
     Num.setFieldLabel("Numéro de compte");
     Num.setAllowBlank(true);
     fieldSet.add(Num, formData);

     final TextField<String> Solde = new TextField<String>();
     Solde.setFieldLabel("Solde");
     Solde.setAllowBlank(true);
     fieldSet.add(Solde, formData);

     final TextField<String> Type = new TextField<String>();
     Type.setFieldLabel("Type");
     Type.setAllowBlank(true);
     fieldSet.add(Type, formData);


     form2.add(fieldSet);

      final AsyncCallback<Collection<String>> callback = new AsyncCallback<Collection<String>>() {

            @Override
            public void onSuccess(Collection<String> result) {
                HTMLPanel tabclients;

                String html="<table align='center' border='1'>";
                html+= "<tr><td><b>Numéro de compte</b></td><td><b>Solde</b></td><td><b>Type</b></td>";
                html+="<td><b>Propriétaire</b></td><td><b>Age</b></td><td><b>Mail</b></td></tr>";
                String cl="";
                for (Iterator it = result.iterator(); it.hasNext();) {
                  cl =  (String) it.next();

                  //Window.alert(cl);
                  String [] tab=cl.split("-");
                  html+= "<tr><td>"+tab[0]+"</td><td>"+tab[1]+"</td><td>"+tab[2]+"</td>";
                  html+= "<td>"+tab[3]+"</td><td>"+tab[5]+"</td><td>"+tab[4]+"</td></tr>";


                }
                html+= "</table>";
                tabclients=new HTMLPanel(html);
                htmlpanel.clear();
                htmlpanel.add(tabclients, "cuerpoc");

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
                    //if(Id.getValue()!=null){
                      //  if(!Id.getValue().toString().trim().equals("")){
                        //    Window.alert("hola "+Id.getValue().toString().trim());
                          //  getService().chargeComptesByClient(Id.getValue().toString(), callback);
                        //}else{
                            
                          //  getService().chargeComptesCrit((Num.getValue()==null)?"":Num.getValue().toString(), (Type.getValue() == null)?"":Type.getValue().toString(),(Solde.getValue() == null)?"":Solde.getValue().toString(),  callback);
                            
                       // }
                    //}else{
                        
                           getService().chargeComptesCrit((Num.getValue()==null)?"":Num.getValue().toString(), (Type.getValue() == null)?"":Type.getValue().toString(),(Solde.getValue() == null)?"":Solde.getValue().toString(),  callback);
                        
                   // }
                }else{
                       getService().chargeComptes(callback);
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