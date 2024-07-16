package Crud.veterinaria.Repository;

import Crud.veterinaria.Model.Mascota;
import Crud.veterinaria.Model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MascotaRepository extends CrudRepository<Mascota,Long> {
    @Query("SELECT m FROM Mascota m WHERE m.usuario.id = :id")
    List<Mascota> findAllMascotaByUsuario(@Param("id") Long id);

}
