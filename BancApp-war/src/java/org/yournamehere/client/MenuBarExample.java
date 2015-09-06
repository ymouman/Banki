/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.client;

    /*
   2.  * Ext GWT 2.2.1 - Ext for GWT
   3.  * Copyright(c) 2007-2010, Ext JS, LLC.
   4.  * licensing@extjs.com
   5.  *
   6.  * http://extjs.com/license
   7.  */
   // package com.extjs.gxt.samples.client.examples.toolbar;

//   import com.extjs.gxt.samples.resources.client.Resources;
import com.extjs.gxt.ui.client.event.ComponentEvent;
 import com.extjs.gxt.ui.client.event.SelectionListener;
   import com.extjs.gxt.ui.client.widget.ContentPanel;
   import com.extjs.gxt.ui.client.widget.LayoutContainer;

   import com.extjs.gxt.ui.client.widget.layout.FlowData;
   import com.extjs.gxt.ui.client.widget.menu.Menu;
   import com.extjs.gxt.ui.client.widget.menu.MenuBar;
   import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
   import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
   import com.google.gwt.user.client.Element;
   import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;

  
   public class MenuBarExample extends LayoutContainer {
     private ContentPanel panel;
     private HTMLPanel htmlpanel;
     public MenuBarExample(){
         panel = new ContentPanel();
         panel.setHeading("Gestion bancaire");
         panel.setSize(800,60);
     }
     @Override
     protected void onRender(Element parent, int index) {
       super.onRender(parent, index);

       Menu menu = new Menu();

       MenuItem item1 = new MenuItem("Nouveau client");
       menu.add(item1);

       MenuItem item2 = new MenuItem("Rechercher des clients");
       menu.add(item2);

       MenuItem item3 = new MenuItem("Supprimer des clients");
       menu.add(item3);

       item1.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");
                
            }

            @Override
            public void handleEvent(ComponentEvent e){
                
                htmlpanel.clear();
                htmlpanel.add(new AddClient(), "corp");
                
           }
        });

        item2.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){
                htmlpanel.clear();
                htmlpanel.add(new SearchClient(), "corp");
           }
        });

        item3.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){
                htmlpanel.clear();
                htmlpanel.add(new ListSupClient(), "corp");
           }
        });
               

       
       MenuBar bar = new MenuBar();
       bar.setBorders(true);
       bar.setStyleAttribute("borderTop", "none");
       bar.add(new MenuBarItem("Gestion des clients", menu));

       Menu sub2 = new Menu();
       MenuItem cpt1=new MenuItem("Créer un compte");
       MenuItem cpt2=new MenuItem("Supprimer un compte");
       MenuItem cpt3=new MenuItem("Rechercher des comptes");
       MenuItem cpt4=new MenuItem("Opérations sur les comptes");
       cpt1.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){

                htmlpanel.clear();
                htmlpanel.add(new AddCompte(), "corp");
           }
        });
        
        cpt2.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){
                htmlpanel.clear();
                htmlpanel.add(new ListSupCompte(), "corp");
           }
        });

        cpt3.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){
                 htmlpanel.clear();
                htmlpanel.add(new SearchCompte(), "corp");
           }
        });

        cpt4.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){
                Window.alert("hello");
           }
        });
        
       sub2.add(cpt1);
       sub2.add(cpt2);
       sub2.add(cpt3);
       sub2.add(new SeparatorMenuItem());
       sub2.add(cpt4);
       //Les opérations
       MenuItem op1=new MenuItem("Débiter un compte");
       MenuItem op2=new MenuItem("Créditer un compte");
       MenuItem op3=new MenuItem("Transferts");
       op1.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){
                htmlpanel.clear();
                htmlpanel.add(new DebiterCompte(), "corp");
           }
        });

        op2.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){
                htmlpanel.clear();
                htmlpanel.add(new CrediterCompte(), "corp");
           }
        });

        op3.addSelectionListener(new SelectionListener(){

            @Override
            public void componentSelected(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet.");

            }

            @Override
            public void handleEvent(ComponentEvent e){
                htmlpanel.clear();
                htmlpanel.add(new TransCompte(), "corp");
           }
        });

       //
       Menu operations=new Menu();
       operations.add(op1);
       operations.add(op2);
       operations.add(op3);
       
       
       cpt4.setSubMenu(operations);


       MenuBarItem edit = new MenuBarItem("Gestion des comptes", sub2);
       bar.add(edit);
       bar.setSize(200, 100);
     
       
       
       panel.setTopComponent(bar);
       String html="<table border='0' align='center'><tr><td><div id='corp'/></td></tr></table>";
       
       htmlpanel=new HTMLPanel(html);
       htmlpanel.setSize("500", "500");

       panel.add(htmlpanel);
       panel.repaint();


       

       add(panel, new FlowData(10));
    }

 }
