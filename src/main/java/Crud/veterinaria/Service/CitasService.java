package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Citas;

import java.util.ArrayList;
import java.util.Optional;

public interface CitasService {

    ArrayList<Citas> getAllCitas();

    Optional<Citas> getCitasById(long id);

    Optional<Citas> updateCitas(Citas c, long id);

    Citas saveCitas(Citas c);

    boolean deleteCitas(long id);

    boolean deleteAllCitas();

    ArrayList<Citas> findAllCitasByMascota(long mascotaId);

}
