package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Mascota;

import java.util.ArrayList;
import java.util.Optional;

public interface MascotaService {

    ArrayList<Mascota> getAllMascota();

    Optional<Mascota> getMascotaById(long id);


    //TODO:CAMBIAR NOMBRE DEL PARAMETRO A usuarioId
    ArrayList<Mascota> findAllMascotaByUsuario(long usuarioId);

    Mascota saveMascota(Mascota m);

    boolean deleteMascotaById(long id);
//pregunta IA
    Optional<Mascota> updateMascota(Mascota m, long id);

    boolean deleteAllMascota ();

}
