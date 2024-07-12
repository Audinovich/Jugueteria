package Usuarios;


import Crud.veterinaria.Model.Mascota;
import Crud.veterinaria.Model.Practica;
import Crud.veterinaria.Model.Usuario;

import Crud.veterinaria.Service.MascotaService;
import Crud.veterinaria.Service.PracticaService;
import Crud.veterinaria.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class Empleado extends Usuario {

    @Autowired
    private MascotaService mascotaService;
    private PracticaService practicaService;
    private UsuarioService usuarioService;

    //ALTA-BAJA-MODIFICACION MASCOTA

    public void crearMascota(Mascota m) {
        Mascota nuevaMascota = mascotaService.saveMascota(m);
        if (nuevaMascota != null) {
            System.out.println("Mascota creada con éxito: " + nuevaMascota.getNombre() +" "+ nuevaMascota.getId());
        } else {
            System.out.println("Error al crear la mascota.");
        }
    }

    public void eliminarMascota(Long id) {
        Optional<Mascota> mascota = mascotaService.getMascotaById(id);
        if (mascota.isPresent()) {
            Mascota m = mascota.get();
            Boolean eliminado = mascotaService.deleteMascotaById(id);
            if (eliminado) {
                System.out.println("Mascota eliminada con éxito: " + m.getNombre() + " con ID " + m.getId());
            } else {
                System.out.println("Error al eliminar la mascota con ID " + id);
            }
        } else {
            System.out.println("No se encontró la mascota con ID " + id);
        }
    }

    // Implementa los demás métodos de manera similar utilizando los servicios correspondientes

    // Aquí puedes agregar más métodos específicos de Empleado si es necesario
}