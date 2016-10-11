package edu.pucmm.sbv.servicios

import edu.pucmm.sbv.dominios.Estudiante
import edu.pucmm.sbv.repositorios.EstudianteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

/**
 * Created by vacax on 11/10/16.
 */
@Service
class BootStrap {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @PostConstruct
    public void bootStrap(){
        println("Iniciando la carga automatica de estudiantes.")
        (1..100).each {
            Estudiante estudiante = new Estudiante(matricula: ""+it, nombre: "Estudiante ${it}", email: "");
            estudianteRepository.save(estudiante);
        }
        println("Carga inicio finalizada....")
    }

}
