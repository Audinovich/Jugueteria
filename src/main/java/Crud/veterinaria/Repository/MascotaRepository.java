package Crud.veterinaria.Repository;

import Crud.veterinaria.Model.Mascota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MascotaRepository extends CrudRepository<Mascota,Long> {
}
