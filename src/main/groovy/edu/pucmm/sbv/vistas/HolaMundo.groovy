package edu.pucmm.sbv.vistas

import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Button
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

/**
 * Created by vacax on 11/10/16.
 */
@SpringUI(path = "/holamundo")
@Theme("valo")
class HolaMundo extends UI {

    @Override
    protected void init(VaadinRequest request) {
        println("Iniciando la vista en Vaadin...")

        //indicando el contenedor...
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label("Hola Mundo en Vaadin :D"))

        TextField textField = new TextField()
        textField.description="Ayuda del componente..."
        textField.inputPrompt="Nombre";
        Button boton=new Button("Presiona", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
               Notification.show("Hola ${textField.value}", Notification.Type.HUMANIZED_MESSAGE)
            }
        })

        HorizontalLayout hl=new HorizontalLayout()
        hl.addComponent(textField)
        hl.addComponent(boton)

        verticalLayout.addComponent(hl);

        setContent(verticalLayout);
    }
}
