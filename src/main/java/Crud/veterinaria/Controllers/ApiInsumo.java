package Crud.veterinaria.Controllers;


import Crud.veterinaria.Model.Insumo;
import Crud.veterinaria.Service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("Insumo")
public class ApiInsumo {

    @Autowired
    InsumoService insumoService;

    @GetMapping("/all")
    ArrayList<Insumo> getAllInsumo() {
        return insumoService.getAllInsumo();
    }

    @GetMapping("/find/{id}")
    Optional<Insumo> getInsumoById(@PathVariable("id") long id) {
        return insumoService.getInsumoById(id);
    }

    @PutMapping("/edit/{id}")
    Optional<Insumo> updateInsumo(@RequestBody Insumo i, @PathVariable("id") long id) {
        return insumoService.updateInsumo(i, id);
    }

    @DeleteMapping("/delete/{id}")
    Optional<Insumo> deleteInsumo(@PathVariable("id") long id) {
        return insumoService.deleteInsumo(id);
    }

    @DeleteMapping("/delete/all")
    public String deleteAllInsumo() {
        boolean resultado = insumoService.deleteAllInsumo();
        if (resultado) {
            return "Se han eliminado los todos los insumos";
        } else {
            return "No se han eliminado los insumos";
        }
    }


    @PostMapping("/save")
    public Insumo saveInsumo(@RequestBody Insumo i) {
        return insumoService.saveInsumo(i);
    }

}

