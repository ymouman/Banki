/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Main entry point.
 *
 * @author younes
 */
public class MainEntryPoint implements EntryPoint {
    /**
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {


        RootPanel.get("corps").add(new MenuBarExample());
    }
}
