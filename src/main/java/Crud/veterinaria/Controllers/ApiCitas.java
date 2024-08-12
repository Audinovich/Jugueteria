package Crud.veterinaria.Controllers;

import Crud.veterinaria.Model.Citas;
import Crud.veterinaria.Service.CitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
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

    @PutMapping("/edit/{citaId}")
    public Optional<Citas> updateCitas(@RequestParam Citas c, @PathVariable("citaId") long id) {
        return citasService.updateCitas(c, id);
    }

    @PostMapping("/save")
    public Citas saveCitas(@RequestBody Citas c, HttpSession session) {
        Citas savedCita = citasService.saveCitas(c);
        session.setAttribute("cita_id", savedCita.getId()); // Almacena el ID de la cita en la sesión
        return savedCita;
    }

    @GetMapping("/find/{citaId}")
    public Optional<Citas> getCitasById(@PathVariable("citaId") long id) {
        return citasService.getCitasById(id);
    }

    @DeleteMapping("/delete/{CitaId}")
    public String deleteCitas(@PathVariable("CitaId") long id) {
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
        } else {
            return "No se han podido eliminar las citas";
        }
    }
}