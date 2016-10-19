package edu.pucmm.sbv.vistas

import com.vaadin.annotations.Theme
import com.vaadin.server.UserError
import com.vaadin.server.VaadinRequest
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.Panel
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import edu.pucmm.sbv.vistas.addons.PruebaCalendario
import edu.pucmm.sbv.vistas.addons.PruebaQrCode
import edu.pucmm.sbv.vistas.basico.BasicoPanel
import edu.pucmm.sbv.vistas.basico.Formularios

/**
 * Created by vacax on 17/10/16.
 */
@SpringUI(path = "/indice")
@Theme("valo")
class IndexUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout verticalLayout = new VerticalLayout()

        Button button1 = new Button("Manejo Básico", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                setContent(new BasicoPanel());
            }
        })

        Button botonError = new Button("Botón de Error", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                Notification.show("Un Error...", Notification.Type.ERROR_MESSAGE)
                event.button.setComponentError(new UserError("Mostrando un error..."))
            }
        })

        Button botonFormularios = new Button("Formularios", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                setContent(new Formularios());
            }
        })

        Button botonCalendario = new Button("Calendario", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                setContent(new PruebaCalendario());
            }
        })

        Button botonQrCode = new Button("QrCode", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                setContent(new PruebaQrCode());
            }
        })

        VerticalLayout vli = new VerticalLayout()

        vli.addComponent(button1)
        vli.addComponent(botonError)
        vli.addComponent(botonFormularios)
        vli.addComponent(botonCalendario)
        vli.addComponent(botonQrCode)

        //propiedades del layout.
        vli.setComponentAlignment(button1, Alignment.MIDDLE_CENTER);
        vli.setComponentAlignment(botonError, Alignment.MIDDLE_CENTER);
        vli.setComponentAlignment(botonFormularios, Alignment.MIDDLE_CENTER);
        vli.setComponentAlignment(botonCalendario, Alignment.MIDDLE_CENTER);
        vli.setComponentAlignment(botonQrCode, Alignment.MIDDLE_CENTER);

        //
        verticalLayout.addComponent(vli)
        verticalLayout.setSizeFull();

        setContent(verticalLayout);
    }
}
