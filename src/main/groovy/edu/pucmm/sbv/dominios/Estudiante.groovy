package edu.pucmm.sbv.dominios

import javax.persistence.Entity
import javax.persistence.Id

/**
 * Created by vacax on 11/10/16.
 */
@Entity
class Estudiante implements  Serializable{

    @Id
    String matricula;
    String nombre;
    String email;

}
