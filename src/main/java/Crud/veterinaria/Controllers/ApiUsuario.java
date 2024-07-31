package Crud.veterinaria.Controllers;


import Crud.veterinaria.Model.Usuario;
import Crud.veterinaria.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
//Se utiliza para mapear solicitudes web  (GET, POST, PUT, DELETE, etc.), a métodos específicos en controladores
@RequestMapping("Usuario")
public class ApiUsuario {

    @Autowired
    UsuarioService usuarioService;


    //SOLCITUDES HTTP GET
    @GetMapping("/all")
    public ArrayList<Usuario> getAllUsuario() {
        return usuarioService.getAllUsuario();
    }

    //SOLCITUDES HTTP PUT
    @PutMapping("/edith/{id}")
    public Optional<Usuario> updateUsuario(@RequestBody Usuario u, @PathVariable("id") long id) {
        return usuarioService.updateUsuario(u, id);
    }

    //SOLCITUDES HTTP PUT
    @GetMapping("/find/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable("id") long id) {
        return usuarioService.getUsuarioById(id);
    }

    //SOLCITUDES HTTP POST
    @PostMapping("/save")
    public Usuario saveUsuario(@RequestBody Usuario u) {
        return usuarioService.saveUsuario(u);
    }

    ////SOLCITUDES HTTP DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteUsuarioById(@PathVariable("id") long id) {
        if (usuarioService.deleteUsuarioById(id))
            return "se ha eliminado el Usuario";
        else
            return " No se ha eliminado el Usuario";
    }

    @DeleteMapping("/delete/all")
    public String deleteAllUsuario() {
        boolean resultado = usuarioService.deleteAllUsuario();
        if (resultado) {
            return "Se han eliminado todos los usuarios";
        } else {
            return "No se han eliminado todos los usuarios";
        }
    }


}
