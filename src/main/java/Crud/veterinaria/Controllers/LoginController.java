package Crud.veterinaria.Controllers;

import Crud.veterinaria.Model.Usuario;
import Crud.veterinaria.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
// MVC - FRONT END
@Controller
//RESCTCONTROLLER _ APLICATIVO TIPO REST
public class LoginController {

    @GetMapping("/Alta.html")
    public String showAlta() {
        return "Alta";
    }

    @RequestMapping("/Index.html")
    public String index() {
        return "Index";
    }

    @RequestMapping("/Login.html")
    public String login() {
        return "Login";
    }


    //CONSTRUCTOR
    // inyecta la interfaz de usuario y por constructor , para poder utilizarla
    private final UsuarioService usuarioService;

    //se llama cuando se crea una nueva instancia de una clase.
    public LoginController(UsuarioService usuarioService) {
        //Inyección de Dependencia- asigna el parámetro usuarioService al campo usuarioService de la clase. Esto permite que el controlador use los métodos y funcionalidades de UsuarioService.
        this.usuarioService = usuarioService;
    }

    //solo recibe POST

    //validacion de datos con base de datos
    @PostMapping("/submit-login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {

        System.out.println("Intento de login - name: " + name + " - password: " + password);
        Usuario usuario = usuarioService.authenticate(name, password);

        if (usuario != null) {
            System.out.println("ingresado CORRECTAMENTE - name: " + name + " - password: " + password);
            System.out.println("ID del usuario: " + usuario.getId());
            model.addAttribute("usuario_id", usuario.getId()); // Agrega el ID del usuario al modelo
            return "Main";
        } else {
            System.out.println("INGRESO FALLIDO- name: " + name + " - password: " + password);
            return "Login";
        }
    }
}