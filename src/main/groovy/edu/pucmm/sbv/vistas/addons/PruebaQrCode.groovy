package edu.pucmm.sbv.vistas.addons

import com.vaadin.ui.Alignment
import com.vaadin.ui.VerticalLayout
import fi.jasoft.qrcode.QRCode

/**
 * Created by vacax on 18/10/16.
 */
class PruebaQrCode extends VerticalLayout {

    public PruebaQrCode(){
        QRCode code = new QRCode();
        code.setValue("The quick brown fox jumps over the lazy dog");
        code.setWidth("300px")
        code.height = "300px"

        this.setSizeFull();
        this.addComponent(code);
        this.setComponentAlignment(code, Alignment.MIDDLE_CENTER)

    }
}
