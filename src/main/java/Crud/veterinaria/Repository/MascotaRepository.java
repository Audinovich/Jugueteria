package Crud.veterinaria.Repository;

import Crud.veterinaria.Model.Mascota;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MascotaRepository extends CrudRepository<Mascota, Long> {

        // QUERY JPA (+ seguro + consistente)
    @Query("SELECT m FROM Mascota m WHERE m.usuario.id = :usuarioId")

        //NATIVA
       // @Query(value = "SELECT * FROM basedatos.mascota WHERE usuarioId = :usuarioId",nativeQuery = true)

    List<Mascota> findAllMascotaByUsuario(@Param("usuarioId") Long usuarioId);

}
