package edu.pucmm.sbv.controladores

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by vacax on 11/10/16.
 */
@RestController
@RequestMapping("/rest/estudiante")
class EstudianteController {

    @RequestMapping("/")
    public String index(){
       return "Hola Mundo...";
    }
}
