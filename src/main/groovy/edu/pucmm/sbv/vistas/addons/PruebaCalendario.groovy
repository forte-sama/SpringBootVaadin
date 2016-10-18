package edu.pucmm.sbv.vistas.addons

import com.vaadin.addon.calendar.ui.Calendar
import com.vaadin.ui.VerticalLayout

/**
 * Created by vacax on 18/10/16.
 */
class PruebaCalendario extends VerticalLayout {

    //Utilizando el calendario.
    Calendar calendario;

    public PruebaCalendario(){

        calendario = new Calendar()
        calendario.setLocale(Locale.US);
        calendario.setFirstVisibleDayOfWeek(2);   //Lunes
        calendario.setLastVisibleDayOfWeek(6);   // Viernes
        calendario.setFirstVisibleHourOfDay(8); // 8 am
        calendario.setLastVisibleHourOfDay(17); // 5 pm
        calendario.setSizeFull();

        this.setSizeFull();
        this.addComponent(calendario);
    }
}
