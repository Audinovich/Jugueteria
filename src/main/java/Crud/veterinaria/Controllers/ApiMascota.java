package Crud.veterinaria.Controllers;


import Crud.veterinaria.Model.Mascota;
import Crud.veterinaria.Model.Usuario;
import Crud.veterinaria.Service.MascotaService;
import Crud.veterinaria.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("Mascota")
public class ApiMascota {

    @Autowired
    MascotaService mascotaService;

    @Autowired
    UsuarioService usuarioService;


    @GetMapping("/all")
    public ArrayList<Mascota> getAllMascota() {
        return mascotaService.getAllMascota();
    }

    @GetMapping("/find/{id}")
    public Optional<Mascota> getMascotaById(@PathVariable("id") long id) {
        return mascotaService.getMascotaById(id);
    }


    @GetMapping("/findAll/{usuarioId}")
    public ArrayList<Mascota> findAllMascotaByUsuario(@PathVariable("usuarioId") long usuarioId) {
        return mascotaService.findAllMascotaByUsuario(usuarioId);
    }


    @PutMapping("/edith/{id}")
    public Optional<Mascota> updateMascota(@RequestBody Mascota m, @PathVariable("id") long id) {


        // Usuario usuario = usuarioService.getUsuarioById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario No Encontrado"));
        //m.setUsuario(usuario);
        return mascotaService.updateMascota(m, id);
    }

    @PostMapping("/save")
    public Mascota saveMascota(@RequestBody Mascota m) {
        Usuario usuario = usuarioService.getUsuarioById(m.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuario No Encontrado"));
        m.setUsuario(usuario);
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
    public String deleteAllMascota() {
        boolean resultado = mascotaService.deleteAllMascota();
        if (resultado) {
            return "Se han eliminado todas las Mascotas";
        } else {
            return "No se  han podido eliminar todas las Mascotas";
        }
    }

}
