package com.example.starter.base;

import com.example.starter.base.osgi.IconResource;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ElementConstants;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
// Need to explicitly declare the Lumo until https://github.com/vaadin/flow/issues/4847 is fixed
@Theme(Lumo.class)
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView() {
        // Below demonstrates that the image is available at the OSGi bundle
        // specific 'absolute' (within the bundle) path, which is exposed to
        // be used via HTTP.
        Image icon = new Image("/icons/icon.png", "Icon");
        icon.getElement().setProperty(ElementConstants.STYLE_WIDTH, 48);
        icon.getElement().setProperty(ElementConstants.STYLE_HEIGHT, 48);
        icon.getElement().setProperty("border", 2);

        Button button = new Button("Click me", icon,
                event -> Notification.show("Clicked!"));
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.setHeight("60px");
        add(button);
    }
}
