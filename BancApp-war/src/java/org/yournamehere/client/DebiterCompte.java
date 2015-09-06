/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.client;



// import com.extjs.gxt.samples.resources.client.TestData;
 //import com.extjs.gxt.samples.resources.client.model.Stock;

 import com.extjs.gxt.ui.client.Style.HorizontalAlignment;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

// import com.extjs.gxt.samples.resources.client;

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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
 import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import java.util.Collection;
import java.util.Iterator;

/*import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import web.PostClient;
import webservice.BancWebInterface;
import webservice.BancWebservicesException;
*/
 public class DebiterCompte extends LayoutContainer {

   private VerticalPanel vp;
   private Label lblServerReply;
   //private ComboBox combo;
   private FormData formData;
   private ListBox lst;
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
     form2.setHeading("Débiter un compte bancaire");
     form2.setWidth(500);
     form2.setHeight(450);
     form2.setLayout(new FlowLayout());

     FieldSet fieldSet = new FieldSet();
     fieldSet.setHeading("Informations de l'opréation");
     //fieldSet.setCheckboxToggle(true);

     FormLayout layout = new FormLayout();
     layout.setLabelWidth(75);
     fieldSet.setLayout(layout);


     String htmltxt="<h3 align='center'>La liste de tous les comptes</h3><table align='center' border='0'><tr><td id='a'></td><td id='b'></td></tr></table><br><br><br>";
     htmltxt+="<table align='center' border='0' width='400'><tr><td><b>- Numéro de compte:</b></td><td id='identc'></td></tr>";
     htmltxt+="<tr><td><b>- Solde:</b></td><td id='solde'></td></tr>";
     htmltxt+="<tr><td><b>- Type:</b></td><td id='type'></td></tr></table><br>";
     htmltxt+="<h3 align='center'>Le propriétaire du compte</h3><table align='center' border='0' width='400'>";
     htmltxt+="<tr><td><b>- Mr.</b></td><td id='nomprop'></td></tr>";
     htmltxt+="<tr><td><b>- Age</b></td><td id='ageprop'></td></tr>";
     htmltxt+="<tr><td><b>- Mail</b></td><td id='mailprop'></td></tr></table><br><br>";

     htmlpanel =new HTMLPanel(htmltxt);
     //htmlpanel.add(new Label(""), "a");

     lst=new ListBox();
     getComptes();
     htmlpanel.add(lst, "b");

     final Label identc=new Label();
     final Label nomprop=new Label();
     final Label solde= new Label();
     final Label type= new Label();
     final Label ageprop=new Label();
     final Label mailprop=new Label();

     htmlpanel.add(identc, "identc");
     htmlpanel.add(nomprop, "nomprop");
     htmlpanel.add(solde, "solde");
     htmlpanel.add(type, "type");
     htmlpanel.add(mailprop, "mailprop");
     htmlpanel.add(ageprop, "ageprop");



     lst.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {



                                        String[] tab=lst.getValue(lst.getSelectedIndex()).split("-");

                                        identc.setText(tab[0]);
                                        solde.setText(tab[1]);
                                        type.setText(tab[2]);
                                        nomprop.setText(tab[3]);
                                        mailprop.setText(tab[4]);
                                        ageprop.setText(tab[5]);



				}
			});

     fieldSet.add(htmlpanel, formData);

     final TextField<String> Solde = new TextField<String>();
     Solde.setFieldLabel("Montant à débiter");
     Solde.setAllowBlank(false);
     fieldSet.add(Solde, formData);

     form2.add(fieldSet);

     final AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onSuccess(Boolean result) {
                lblServerReply.setText(result?"Compte débité avec succès":"Echec suppression!");
                getComptes();
            }

            @Override
            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed");
            }
        };
     lblServerReply=new Label();



     form2.setButtonAlign(HorizontalAlignment.CENTER);
     Button save=new Button("Débiter");
     Button cancel=new Button("Cancel");
     save.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){

                String msgerror="";

                if(Solde.getValue()==null){
                    msgerror=msgerror.concat("- le champ solde est vide!\n");
                }
                else{
                    String valeur=Solde.getValue().toString();
                    float intvalue=0;
                    try{
                     intvalue=Float.parseFloat(valeur);
                    }catch(NumberFormatException ex){
                        msgerror=msgerror.concat("- le champ solde est invalide  !\n");
                    }

                }


                String[] tab=lst.getValue(lst.getSelectedIndex()).split("-");
                String numcompte=tab[0];



                if(msgerror == null ? "" != null : !msgerror.equals("")){
                    Window.alert(msgerror);
                }else {
                    String debit=Solde.getValue().toString();
                    
                    getService().DebiterCompte(numcompte, debit, callback);

                    solde.setText(String.valueOf(Float.valueOf(tab[1])+Float.valueOf(debit)));

                    //lst.clear();
                    
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

    public void getComptes() {

    final AsyncCallback<Collection<String>> callback1 = new AsyncCallback<Collection<String>>() {


            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Erreur RPC: rechargement des clients interrompu !!");
            }

            @Override
            public void onSuccess(Collection<String> result) {
                //Window.alert("chargement des clients hhhhhh "+String.valueOf(result.size()));
                String cl="";
                lst.clear();
                for (Iterator it = result.iterator(); it.hasNext();) {
                  cl =  (String) it.next();

                  //Window.alert(cl);

                  lst.addItem(cl);

                }
            }



        };

        getService().chargeComptes(callback1);

  }

 }/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

