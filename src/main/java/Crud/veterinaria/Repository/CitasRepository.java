package Crud.veterinaria.Repository;

import Crud.veterinaria.Model.Citas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CitasRepository extends CrudRepository<Citas ,Long > {

    @Query("SELECT c FROM Citas c WHERE c.mascota.id = :mascotaId")
    List<Citas> findAllCitasByMascota(@Param("mascotaId")Long mascotaId);

}
