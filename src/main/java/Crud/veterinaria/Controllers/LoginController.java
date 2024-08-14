package Crud.veterinaria.Controllers;
import Crud.veterinaria.Service.PracticaService;
import Crud.veterinaria.Model.Practica;
import Crud.veterinaria.Model.Usuario;
import Crud.veterinaria.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    @GetMapping("/Mascotas.html")
    public String mascotas(HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuario_id"); // Obtiene el ID del usuario desde la sesión
        session.setAttribute("usuario_id", usuarioId); // Almacena el ID del usuario en la sesión
        return "Mascotas";
    }

    @GetMapping("/Citas.html")
    public String citas(HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("usuario_id"); // Obtiene el ID del usuario desde la sesión
        session.setAttribute("usuario_id", usuarioId); // Almacena el ID del usuario en la sesión
        return "Citas";
    }


    @GetMapping("/all")
    public String Practicas(){
    return "Practicas";
    };


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
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session) {

        System.out.println("Intento de login - nombre: " + name + " - contraseña: " + password);
        Usuario usuario = usuarioService.authenticate(name, password);

        if (usuario != null) {
            System.out.println("Ingresado CORRECTAMENTE - nombre: " + name + " - contraseña: " + password);
            System.out.println("ID del usuario: " + usuario.getId());
            session.setAttribute("usuario_id", usuario.getId()); // Almacena el ID del usuario en la sesión
            return "Usuario";
        } else {
            System.out.println("INGRESO FALLIDO - nombre: " + name + " - contraseña: " + password);
            return "Login";
        }
    }
}