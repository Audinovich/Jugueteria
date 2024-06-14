package jugueteria.demo.Controllers;


import jugueteria.demo.Model.Articulo;
import jugueteria.demo.Service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping ("Catalogo")
public class Api {

    @Autowired
    ArticuloService articuloService;


    @GetMapping("/all")
    public ArrayList<Articulo> getAllArticulo() {return articuloService.getAllArticulo();}

    @GetMapping("/find/{id}")
    public Optional<Articulo> getArticuloById(@PathVariable("id")long id){
        return articuloService.getArticuloById(id);
    }

    @PostMapping("/save")
    public Articulo saveArticulo(@RequestBody Articulo a){
        return articuloService.saveArticulo(a);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteArticuloById(@PathVariable("id")long id){
        if(articuloService.deleteArticuloById(id))
            return "se ha eliminado el Articulo";
        else
            return " No se ha eliminado el Articulo";
    }

}
