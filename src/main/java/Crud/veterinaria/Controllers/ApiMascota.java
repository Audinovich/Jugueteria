package Crud.veterinaria.Controllers;


import Crud.veterinaria.Model.Mascota;
import Crud.veterinaria.Service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("Mascota")
public class ApiMascota {

    @Autowired
    MascotaService mascotaService;


    @GetMapping("/all")
    public ArrayList<Mascota> getAllMascota() {
        return mascotaService.getAllMascota();
    }

    @GetMapping("/find/{id}")
    public Optional<Mascota> getMascotaById(@PathVariable("id") long id) {
        return mascotaService.getMascotaById(id);
    }

    @PutMapping("/edith/{id}")
    public Optional<Mascota> updateMascota(@RequestBody Mascota m, @PathVariable("id") long id) {
        return mascotaService.updateMascota(m, id);
    }

    @PostMapping("/save")
    public Mascota saveMascota(@RequestBody Mascota m) {
        return mascotaService.saveMascota(m);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMascotaById(@PathVariable("id") long id) {
        if (mascotaService.deleteMascotaById(id))
            return "se ha eliminado La Mascota";
        else
            return " No se ha eliminado La Mascota";
    }

    @DeleteMapping("/delete/all")
    public String deleteAllMascota(){
        boolean resultado = mascotaService.deleteAllMascota();
        if(resultado){
            return  "Se han eliminado todas las Mascotas";
        }else{
            return "No se  han podido eliminar todas las Mascotas";
        }
    }

}
