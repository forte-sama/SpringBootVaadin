package edu.pucmm.sbv.vistas.estudiante

import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.event.ShortcutAction
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.Button
import com.vaadin.ui.FormLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.TextField
import edu.pucmm.sbv.dominios.Estudiante
import edu.pucmm.sbv.servicios.EstudianteService
import edu.pucmm.sbv.vistas.MainUI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by vacax on 11/10/16.
 */
@Component
@UIScope
class EstudianteForm extends FormLayout{

    @Autowired
    private EstudianteService service;
    private Estudiante estudiante;
    private MainUI myUI;

    TextField matricula=new TextField("Matricula");
    TextField nombre=new TextField("Nombre");
    TextField email=new TextField("Email");
    Button save = new Button("Guardar");
    Button delete= new Button("Borrar");


    public EstudianteForm() {

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        buttons.setSpacing(true);

        //Incluyendo los botones
        addComponents(matricula, nombre, email, buttons);

        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        save.addClickListener(new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                save();
            }
        })
        delete.addClickListener(new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                delete();
            }
        })
    }

    public void setMainUI(MainUI mainUI){
        myUI = mainUI;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        BeanFieldGroup.bindFieldsUnbuffered(estudiante, this);

        // Show delete button for only customers already in the database
        //delete.setVisible(estudiante.isPersisted());
        setVisible(true);
        nombre.selectAll();
    }

    private void delete() {
        service.delete(estudiante);
        myUI.updateList();
        setVisible(false);
    }

    private void save() {
        println("La variable: "+service)
        service.save(estudiante);
        myUI.updateList();
        setVisible(false);
    }

}
