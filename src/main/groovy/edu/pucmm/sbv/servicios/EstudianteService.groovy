package edu.pucmm.sbv.servicios

import edu.pucmm.sbv.dominios.Estudiante
import edu.pucmm.sbv.repositorios.EstudianteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by vacax on 11/10/16.
 */
@Service
class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    public List<Estudiante> listarTodosEstudiantesPorNombre(String nombre){
        println("Consultado: ${nombre}")
        List<Estudiante> lista =  null;
        if(nombre.trim().isEmpty()) {
            return estudianteRepository.findAll();
        }else{
            return estudianteRepository.findAllByNombre(nombre);
        }
        return lista;
    }

    @Transactional
    public Estudiante save(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return estudiante;
    }

    @Transactional
    public boolean delete(Estudiante estudiante) {
        estudianteRepository.delete(estudiante);
        return true;
    }
}
