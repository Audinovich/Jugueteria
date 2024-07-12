package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Practica;

import java.util.ArrayList;
import java.util.Optional;

public interface PracticaService {

    ArrayList<Practica> getAllPractica();

    ArrayList<Practica> getAllPracticaByPractica(String practica);

    Optional<Practica> getPracticaById (long id);

    Optional <Practica> updatePractica(Practica p, long id);

    Practica savePractica(Practica p);

    boolean deletePracticaById(Long id);

    boolean deleteAllPractica ();

}
