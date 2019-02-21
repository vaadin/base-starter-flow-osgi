package com.example.starter.base;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ElementConstants;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceEvent;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
// Need to explicitly declare the Lumo until https://github.com/vaadin/flow/issues/4847 is fixed
@Theme(Lumo.class)
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
//@Push // does not work yet-- https://github.com/vaadin/flow/issues/5081
public class MainView extends VerticalLayout {

    public MainView() {

        UI.getCurrent().setPollInterval(500);

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

        FrameworkUtil.getBundle(MainView.class).getBundleContext().addServiceListener(serviceEvent -> {
            String[] objectClass = (String[])
                    serviceEvent.getServiceReference().getProperty("objectClass");

            if (serviceEvent.getType() == ServiceEvent.REGISTERED) {
                this.getUI().ifPresent(ui -> ui.access(() -> this.add(new Paragraph("Service of type " + objectClass[0] + " registered."))));
            } else if (serviceEvent.getType() == ServiceEvent.UNREGISTERING) {
                this.getUI().ifPresent(ui -> ui.access(() -> this.add(new Paragraph("Service of type " + objectClass[0] + " unregistered."))));
            } else if (serviceEvent.getType() == ServiceEvent.MODIFIED) {
                this.getUI().ifPresent(ui -> ui.access(() -> {
                    this.add(new Paragraph("Service of type " + objectClass[0] + " modified."));
                }));
            }
        });
    }
}
