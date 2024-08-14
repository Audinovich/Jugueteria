package Crud.veterinaria.Controllers;


import Crud.veterinaria.Model.Practica;
import Crud.veterinaria.Service.PracticaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("Practica")
public class ApiPractica {

    @Autowired
    PracticaService practicaService;


    @GetMapping("/all")
    public ArrayList<Practica> getAllPractica(HttpSession session, @RequestParam(value = "practica_id", required = false) String practicaId) {
        if (practicaId != null) {
            session.setAttribute("practica_id", practicaId); // Almacena el ID de la práctica en la sesión
        }
        return practicaService.getAllPractica();
    }

    //El nombre dentro de @PathVariable() debe coincidir con la variable especificada en la ruta del @GetMapping o @PutMapping.
    @GetMapping("/find/{practica}")
    public ArrayList<Practica> getAllPracticaByPractica(@PathVariable("practica") String practica) {
        return practicaService.getAllPracticaByPractica(practica);
    }

    @GetMapping("/find/{id}")
    public Optional <Practica> getPracticaById (@PathVariable("id") long id){
        return practicaService.getPracticaById(id);
    }



    @PutMapping("/edith/{id}")
    public Optional<Practica> modificarPractica(@RequestBody Practica p, @PathVariable("id") long id) {
        return practicaService.updatePractica(p, id);
    }


    @PostMapping("/save")
    public Practica savePractica(@RequestBody Practica p) {
        return practicaService.savePractica(p);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePracticaById(@PathVariable("id") long id) {
        if (practicaService.deletePracticaById(id))
            return "se ha eliminado la Practica";
        else
            return " No se ha eliminado la Practica";
    }

    @DeleteMapping("/delete/all")
    public String deleteAllPractica (){
        boolean resultado = practicaService.deleteAllPractica();

    if(resultado){
        return "Se han eliminado todas las Practicas";
    }else{
        return "No Se han podido eliminar las Practicas";
    }
    }

}


