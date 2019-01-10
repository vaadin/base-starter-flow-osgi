package com.example.starter.anotherbundle;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The main view contains a button and a click listener.
 */
@Route("another")
// Need to explicitly declare the Lumo until https://github.com/vaadin/flow/issues/4847 is fixed
@Theme(Lumo.class)
public class AnotherView extends VerticalLayout {

    public AnotherView() {
        Button button = new Button("Click me in another view",
                event -> Notification.show("Clicked in another view!"));
        add(button);
    }
}
