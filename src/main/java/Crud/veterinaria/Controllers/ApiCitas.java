package Crud.veterinaria.Controllers;


import Crud.veterinaria.Model.Citas;
import Crud.veterinaria.Service.CitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("Citas")
public class ApiCitas {

    @Autowired
    CitasService citasService;


    @GetMapping("/all")
    public ArrayList<Citas> getAllCitas() {
        return citasService.getAllCitas();
    }

    @GetMapping("/findByMascota/{mascotaId}")
    public ArrayList<Citas> findAllCitasByMascota (@PathVariable("mascotaId") long id){
        return citasService.findAllCitasByMascota(id);
    }

    @PutMapping("/edit/{id}")
    public Optional<Citas> updateCitas(@RequestParam Citas c, @PathVariable("id") long id) {
        return citasService.updateCitas(c, id);

    }

    @PostMapping("/save")
    public Citas saveCitas (@RequestBody Citas c ){
        return citasService.saveCitas(c);
    }


    @GetMapping("/find/{id}")
    public Optional<Citas> getCitasById(@PathVariable("id") long id) {
        return citasService.getCitasById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCitas(@PathVariable("id") long id) {
        if (citasService.deleteCitas(id))
            return "Se ha Eliminado la cita";
        else
            return "no se ha eliminado la cita";
    }

    @DeleteMapping("/delete/all")
            public String deleteAllCitas() {
        boolean resultado = citasService.deleteAllCitas();

        if (resultado) {
            return "Se han eliminado todas las Citas";
        }else{
            return "No se han podido eliminar las citas";
        }
    }
}

