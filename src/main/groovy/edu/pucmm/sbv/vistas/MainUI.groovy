package edu.pucmm.sbv.vistas

import com.vaadin.annotations.Theme
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.event.FieldEvents
import com.vaadin.event.SelectionEvent
import com.vaadin.server.FontAwesome
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Button
import com.vaadin.ui.CssLayout
import com.vaadin.ui.Grid
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import edu.pucmm.sbv.dominios.Estudiante
import edu.pucmm.sbv.servicios.EstudianteService
import edu.pucmm.sbv.vistas.estudiante.EstudianteForm
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by vacax on 11/10/16.
 */
@SpringUI(path = "/")
@Theme("valo")
class MainUI extends UI {

    private Grid grid = new Grid();
    private TextField filterText = new TextField();
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private EstudianteForm form;

    @Override
    protected void init(VaadinRequest request) {

        UI.getCurrent().locale=new Locale("en");

        VerticalLayout layout = new VerticalLayout() //creando el layout que estaremos trabajando.

        form.setMainUI(this); //agregando la referencia

        //bloque
        filterText.setInputPrompt("Filtrado Por Nombre");
        filterText.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            void textChange(FieldEvents.TextChangeEvent e) {
                grid.setContainerDataSource(new BeanItemContainer<>(Estudiante.class, estudianteService.listarTodosEstudiantesPorNombre(e.getText())));
            }
        })


        Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
        clearFilterTextBtn.setDescription("Limpiar");
        clearFilterTextBtn.addClickListener(new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent e) {
                filterText.clear();
                updateList();
            }
        })


        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addCustomerBtn = new Button("Nuevo Estudiante");
        addCustomerBtn.addClickListener(new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent event) {
                grid.select(null);
                form.setEstudiante(new Estudiante());
            }
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn);
        toolbar.setSpacing(true);

        grid.setColumns("matricula", "nombre", "email");

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSpacing(true);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(toolbar, main);

        updateList();

        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        form.setVisible(false);

        grid.addSelectionListener(new SelectionEvent.SelectionListener() {
            @Override
            void select(SelectionEvent event) {
                if (event.getSelected().isEmpty()) {
                    form.setVisible(false);
                } else {
                    Estudiante customer = (Estudiante) event.getSelected().iterator().next();
                    form.setEstudiante(customer);
                }
            }
        })

    }

    public void updateList() {
        List<Estudiante> customers = estudianteService.listarTodosEstudiantesPorNombre(filterText.getValue());
        grid.setContainerDataSource(new BeanItemContainer<>(Estudiante.class, customers));
    }
}
