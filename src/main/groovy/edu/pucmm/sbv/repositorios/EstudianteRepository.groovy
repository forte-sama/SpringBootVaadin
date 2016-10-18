package edu.pucmm.sbv.repositorios

import edu.pucmm.sbv.dominios.Estudiante
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by vacax on 11/10/16.
 */
interface EstudianteRepository extends JpaRepository<Estudiante, Integer>  {

    List<Estudiante> findAllByNombreIgnoreCaseContaining(String nombre)
}