package Crud.veterinaria.Repository;

import Crud.veterinaria.Model.Practica;

import Crud.veterinaria.Model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;


@Repository
public interface PracticaRepository extends CrudRepository<Practica,Long> {
    ArrayList<Practica> findAllByPractica(String practica);

}
