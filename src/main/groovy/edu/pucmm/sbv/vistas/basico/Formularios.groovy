package edu.pucmm.sbv.vistas.basico

import com.vaadin.data.Validator
import com.vaadin.data.util.ObjectProperty
import com.vaadin.data.validator.EmailValidator
import com.vaadin.data.validator.IntegerRangeValidator
import com.vaadin.data.validator.NullValidator
import com.vaadin.data.validator.StringLengthValidator
import com.vaadin.ui.Button
import com.vaadin.ui.FormLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout

/**
 * Created by vacax on 18/10/16.
 */
class Formularios extends VerticalLayout {

    //No es necesario instanciar en declaración.
    TextField texto=new TextField("Texto longitud");
    TextField textoEmail=new TextField("Texto Email");
    TextField textoNull=new TextField("Texto Null");
    TextField textoInteger=new TextField("Texto Integer");
    TextField textoCustom=new TextField("Texto Custom");
    Button botonValidar;

    public Formularios(){
       FormLayout form=new FormLayout();

        texto.addValidator(new StringLengthValidator("The name must be 1-10 letters (was {0})", 1, 10, true))
        texto.setImmediate(true); //se valida desde que pierdo el forcus..
        texto.setNullRepresentation("");
        texto.setNullSettingAllowed(true);

        textoEmail.addValidator(new EmailValidator("No es un correo valido..."))
        textoEmail.setNullRepresentation("");
        textoEmail.setNullSettingAllowed(true);
        textoEmail.setImmediate(true);

        textoNull.addValidator(new NullValidator("No permite nullo", false))
        textoNull.setImmediate(true);

        // Cambiando la propiedad del campo a entero...
        ObjectProperty<Integer> property = new ObjectProperty<Integer>(0);
        textoInteger.setPropertyDataSource(property)
        textoInteger.addValidator(new IntegerRangeValidator("El valor {0} no es valido", 100, 200))
        textoInteger.setNullRepresentation("");
        textoInteger.setNullSettingAllowed(true);

        textoCustom.addValidator(new MiValidador())
        textoCustom.setNullRepresentation("");
        textoCustom.setNullSettingAllowed(true);
        textoCustom.setImmediate(true);

        botonValidar=new Button("Validar", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                textoInteger.setValidationVisible(false)
                try {
                    textoInteger.validate();  //de forma individual...

                } catch (Validator.InvalidValueException e) {
                    Notification.show(e.getMessage());
                    textoInteger.setValidationVisible(true);
                }
            }
        })

        HorizontalLayout buttons = new HorizontalLayout(botonValidar);
        buttons.setSpacing(true);

        form.addComponents(texto, textoEmail, textoNull, textoCustom, textoInteger, buttons);
        addComponent(form)
    }
}

class MiValidador implements Validator {
    @Override
    public void validate(Object value) throws Validator.InvalidValueException {
        if (!(value instanceof String && ((String)value) == "20011136"))
            throw new Validator.InvalidValueException("No se acepta esa matricula..");
    }
}
