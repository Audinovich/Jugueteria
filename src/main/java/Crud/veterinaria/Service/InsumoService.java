package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Insumo;

import java.util.ArrayList;
import java.util.Optional;

public interface InsumoService {

    ArrayList<Insumo> getAllInsumo();

    Optional<Insumo> getInsumoById(long id);

    Optional<Insumo> updateInsumo(Insumo i, long id);

    Insumo saveInsumo(Insumo i);

    Optional<Insumo> deleteInsumo(long id);

    boolean deleteAllInsumo();
}
